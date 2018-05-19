package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.common.constant.PayType;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.model.FopPayRecord;
import com.huacainfo.ace.fop.service.*;
import com.huacainfo.ace.fop.vo.FopAssociationQVo;
import com.huacainfo.ace.fop.vo.FopAssociationVo;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.UserCfg;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.DepartmentService;
import com.huacainfo.ace.portal.service.UserCfgService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private DepartmentService departmentService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FopMemberService fopMemberService;

    @Autowired
    private FopPayRecordService fopPayRecordService;

    @Autowired
    private UserCfgService userCfgService;

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
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(1, "协会全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPhoneNumber())) {
            return new MessageResponse(1, "协会号码不能为空！");
        }

        int temp = this.fopAssociationDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "协会名称/办公号码重复！");
        }

        //初始化系统用户
        ResultResponse rs1 = initSysUser(o, userProp);
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

        return new MessageResponse(0, "添加商协会完成");
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
     * 自动分配系统登录用户
     *
     * @param o        obj
     * @param userProp 登录用户
     * @return
     * @throws Exception
     */
    private ResultResponse initSysUser(FopAssociation o, UserProp userProp) throws Exception {
        //portal.department
        Department department = new Department();
        department.setDepartmentId(GUIDUtil.getGUID());
        department.setParentDepartmentId("0010006");//市工商联
        department.setDepartmentName(o.getFullName());
        department.setContactMobile(o.getPhoneNumber());
        department.setSyid(userProp.getActiveSyId());
        department.setAreaCode(userProp.getAreaCode());
        MessageResponse rs1 = departmentService.insertDepartmentWithDepId(department, userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            throw new CustomException(rs1.getErrorMessage());
        }
        //portal.users
        Users initUser = new Users();
        initUser.setDepartmentId(department.getDepartmentId());
        initUser.setAccount(o.getPhoneNumber());
        initUser.setName(o.getFullName());
        initUser.setPassword("123456");
        initUser.setSex("1");
        initUser.setUserLevel("1");
        ResultResponse rs2 = usersService.insertUsersRecord(initUser, userProp, "市工商联 -- 团体注册");
        if (ResultCode.FAIL == rs2.getStatus()) {
            throw new CustomException(rs2.getInfo());
        }
        //分配用户角色  默认分配非会员权限，注册申请成功后，分配会员权限
        Users users = (Users) rs2.getData();
        ResultResponse rs3 = fopMemberService.dispatchRoleRight(users.getUserId(), userProp, new String[]{"5"});
        if (ResultCode.FAIL == rs3.getStatus()) {
            throw new CustomException(rs3.getInfo());
        }
        //插入登录后，默认跳转页配置
        List<UserCfg> cfgList = new ArrayList<>();
        UserCfg userCfg = new UserCfg();
        userCfg.setUserId(users.getUserId());
        userCfg.setCfgKey("portalType");
        userCfg.setCfgValue("5");
        cfgList.add(userCfg);
        userCfgService.saveOrUpdateUserCfg(cfgList, userProp);
        //TODO 分配成功通知


        return new ResultResponse(ResultCode.SUCCESS, "初始化系统用户成功", department);
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
    public MessageResponse deleteFopAssociationByFopAssociationId(String id,
                                                                  UserProp userProp) throws Exception {
        FopAssociation association = fopAssociationDao.selectByPrimaryKey(id);
        if (null == association) {
            return new MessageResponse(ResultCode.FAIL, "企业管理删除完成！");
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
        FopAssociation o = new FopAssociation();
        o.setFullName(name);
        o.setPhoneNumber(phoneNumber);
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(1, "协会全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPhoneNumber())) {
            return new MessageResponse(1, "协会号码不能为空！");
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
        ResultResponse rs1 = initSysUser(o, userProp);
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

        return new MessageResponse(0, "添加商协会完成");
    }
}
