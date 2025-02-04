package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.RandomValidateCode;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.ValidateUtils;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.common.constant.PayType;
import com.huacainfo.ace.fop.dao.FopAssMemberDao;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.model.FopPayRecord;
import com.huacainfo.ace.fop.service.*;
import com.huacainfo.ace.fop.vo.FopAssMemberVo;
import com.huacainfo.ace.fop.vo.FopAssociationQVo;
import com.huacainfo.ace.fop.vo.FopAssociationVo;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fopAssociationService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description:
 */
public class FopAssociationServiceImpl implements FopAssociationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopAssociationDao fopAssociationDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private FopPersonService fopPersonService;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    @Autowired
    private FopPayRecordService fopPayRecordService;

    @Autowired
    private FopAssMemberDao fopAssMemberDao;

    @Autowired
    private SysAccountService sysAccountService;

    @Autowired
    private UsersService usersService;
    @Autowired
    private MessageService messageService;

    /**
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopAssociationVo> findFopAssociationList(FopAssociationQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<FopAssociationVo> rst = new PageResult<FopAssociationVo>();
        List<FopAssociationVo> list = this.fopAssociationDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopAssociationDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopAssociation(FopAssociation o, UserProp userProp)
            throws Exception {
        if (!CommonUtils.isValidMobile(o.getPhoneNumber())) {
            return new MessageResponse(ResultCode.FAIL, "不是手机号码");
        }
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(1, "协会全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPhoneNumber())) {
            return new MessageResponse(1, "协会号码不能为空！");
        }

        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }

        int temp = this.fopAssociationDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "协会名称/办公号码重复！");
        }
        //初始化系统用户
        String password = RandomValidateCode.CreateRadom(8, 2);
        ResultResponse rs1 = sysAccountService.initSysUser(o.getFullName(), o.getPhoneNumber(),
                password, "市工商联 -- 团体注册", userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            return new MessageResponse(ResultCode.FAIL, rs1.getInfo());
        }
        //TODO 构建会长个人信息

        //团体信息入库
        Department department = (Department) rs1.getData();
        o.setDepartmentId(department.getDepartmentId());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("2");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopAssociationDao.insertSelective(o);
        this.dataBaseLogService.log("添加商协会", "商协会", "", o.getId(),
                o.getId(), userProp);

        //自动提交会员申请流程
        MessageResponse rs3 = fopFlowRecordService.submitFlowRecord(GUIDUtil.getGUID(),
                FlowType.MEMBER_JOIN_ASSOCIATION, o.getId(), userProp);
        if (ResultCode.FAIL == rs3.getStatus()) {
            return rs3;
        }
        //自动提交缴费记录
        MessageResponse rs4 = submitPayRecord(o, userProp);
        if (ResultCode.FAIL == rs4.getStatus()) {
            return rs4;
        }
        //注册成功消息推送
        sendRegisterMessage(o.getPhoneNumber(), o.getFullName(), password);

        return new MessageResponse(0, "添加商协会完成");
    }

    private void sendRegisterMessage(String phoneNumber, String fullName, String password) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("name", fullName);
            params.put("password", password);
            messageService.registerMessage(true, phoneNumber, params);
        } catch (Exception e) {
            logger.error("注册成功消息推送失败:\n {}", e);
        }
    }

    /**
     * 功能描述: 自动提交缴费记录
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/7 16:28
     */
    private MessageResponse submitPayRecord(FopAssociation o, UserProp userProp) throws Exception {
        FopPayRecord payRecord = new FopPayRecord();
        payRecord.setRelationId(o.getId());
        payRecord.setRelationType(PayType.PAY_TYPE_MEMBER_JOIN_ASSOCIATION);
        payRecord.setPayCategory(PayType.PAY_CATEGORY_MEMBER_JOIN);
        payRecord.setPayDate(DateUtil.getNowDate());

        return fopPayRecordService.submitPayRecord(payRecord, userProp);
    }

    /**
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopAssociation(FopAssociation o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(1, "协会全称不能为空！");
        }

        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopAssociationDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更企业管理完成！");
    }


    /**
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopAssociationVo> selectFopAssociationByPrimaryKey(String id) throws Exception {
        SingleResult<FopAssociationVo> rst = new SingleResult<FopAssociationVo>();
        rst.setValue(this.fopAssociationDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public ResultResponse selectAssociationByPrimaryKey(UserProp userProp) throws Exception {
        ResultResponse rr = getAssociationId(userProp);
        if (ResultCode.FAIL == rr.getStatus()) {
            return rr;
        }
        String id = (String) rr.getData();
        FopAssociationVo fm = this.fopAssociationDao.selectVoByPrimaryKey(id);
        List<FopAssMemberVo> list = fopAssMemberDao.selectVoByAssociationId(id);
        fm.setList(list);
        return new ResultResponse(ResultCode.SUCCESS, "获取团体详情", fm);
    }

    /**
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopAssociationByFopAssociationId(String id, UserProp userProp) throws Exception {
        FopAssociation association = fopAssociationDao.selectByPrimaryKey(id);
        if (null == association) {
            return new MessageResponse(ResultCode.FAIL, "企业管理删除完成！");
        }
        //注销系统账户
        Users users = usersService.selectByAccount(association.getPhoneNumber());
        MessageResponse rs = usersService.updateUsersStautsByPrimaryKey(users.getUserId(), "0", userProp);
        if (rs.getStatus() == ResultCode.FAIL) {
            return rs;
        }

        association.setLastModifyDate(DateUtil.getNowDate());
        association.setLastModifyUserId(userProp.getUserId());
        association.setLastModifyUserName(userProp.getName());
        association.setStatus("0");

        this.fopAssociationDao.updateByPrimaryKeySelective(association);
        this.dataBaseLogService.log("删除商协会资料", "商协会资料", String.valueOf(id),
                String.valueOf(id), "商协会资料", userProp);
        return new MessageResponse(0, "商协会资料删除完成");
    }

    @Override
    public MessageResponse insertAssociation(String name, String phoneNumber) throws Exception {
        if (!CommonUtils.isValidMobile(phoneNumber)) {
            return new MessageResponse(ResultCode.FAIL, "不是手机号码");
        }
        FopAssociation o = new FopAssociation();
        o.setFullName(name);
        o.setPhoneNumber(phoneNumber);
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(1, "协会全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPhoneNumber())) {
            return new MessageResponse(1, "协会号码不能为空！");
        }

        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }
        int temp = this.fopAssociationDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "协会名称/办公号码重复！");
        }
        UserProp userProp = new UserProp();
        userProp.setActiveSyId("fop");
        userProp.setName("工商联管理员");
        userProp.setUserId("1522198886381");
        //初始化系统用户
        String password = RandomValidateCode.CreateRadom(8, 2);
        ResultResponse rs1 = sysAccountService.initSysUser(o.getFullName(), o.getPhoneNumber(),
                password, "市工商联 -- 企业注册", userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            return new MessageResponse(ResultCode.FAIL, rs1.getInfo());
        }
        //TODO 构建会长个人信息

        //团体信息入库
        Department department = (Department) rs1.getData();
        o.setDepartmentId(department.getDepartmentId());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopAssociationDao.insertSelective(o);
        this.dataBaseLogService.log("添加商协会", "商协会", "", o.getId(),
                o.getId(), userProp);
        //自动提交会员申请流程
        MessageResponse rs3 = fopFlowRecordService.submitFlowRecord(GUIDUtil.getGUID(),
                FlowType.MEMBER_JOIN_ASSOCIATION, o.getId(), userProp);
        if (ResultCode.FAIL == rs3.getStatus()) {
            return rs3;
        }
        //自动提交缴费记录
        MessageResponse rs4 = submitPayRecord(o, userProp);
        if (ResultCode.FAIL == rs4.getStatus()) {
            return rs4;
        }
        //注册成成功，账号信息推送
        sendRegisterMessage(o.getPhoneNumber(), o.getFullName(), password);

        return new MessageResponse(0, "添加商协会完成");
    }

    @Override
    public FopAssociation selectByDepartmentId(String departmentId) throws Exception {
        return fopAssociationDao.selectByDepartmentId(departmentId);
    }


    private ResultResponse getAssociationId(UserProp userProp) throws Exception {
        SingleResult<UsersVo> singleResult = usersService.selectUsersByPrimaryKey(userProp.getUserId());
        UsersVo user = singleResult.getValue();
        if (null == user) {
            return new ResultResponse(1, "没有注册");
        }
        if (CommonUtils.isBlank(user.getDepartmentId())) {
            return new ResultResponse(1, "账户没有绑定团体！");
        }
        FopAssociation fa = fopAssociationDao.selectByDepartmentId(user.getDepartmentId());
        if (null == fa) {
            return new ResultResponse(ResultCode.FAIL, "fop团体不存在");
        }
        return new ResultResponse(ResultCode.SUCCESS, "id获取", fa.getId());
    }


    private MessageResponse validate(FopAssociation o) throws Exception {
        if (!CommonUtils.isBlank(o.getPhoneNumber())) {
            if (!ValidateUtils.Mobile(String.valueOf(o.getPhoneNumber()))) {
                return new MessageResponse(ResultCode.FAIL, "办公号码格式不正确");
            }
        }
        if (!CommonUtils.isBlank(o.getDirectorNum())) {
            if (!ValidateUtils.Number(String.valueOf(o.getDirectorNum()))) {
                return new MessageResponse(ResultCode.FAIL, "理事数量格式不正确，为数字");
            }
        }
        if (!CommonUtils.isBlank(o.getViceNum())) {
            if (!ValidateUtils.Number(String.valueOf(o.getViceNum()))) {
                return new MessageResponse(ResultCode.FAIL, "副会长数量格式不正确，为数字");
            }
        }
        return new MessageResponse(ResultCode.SUCCESS, "数据格式正确");
    }
}
