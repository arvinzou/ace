package com.huacainfo.ace.society.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.society.constant.AuditState;
import com.huacainfo.ace.society.constant.BisType;
import com.huacainfo.ace.society.dao.OrgAdminDao;
import com.huacainfo.ace.society.dao.PersonInfoDao;
import com.huacainfo.ace.society.dao.RegDao;
import com.huacainfo.ace.society.dao.SocietyOrgInfoDao;
import com.huacainfo.ace.society.model.OrgAdmin;
import com.huacainfo.ace.society.model.PersonInfo;
import com.huacainfo.ace.society.model.SocietyOrgInfo;
import com.huacainfo.ace.society.service.AuditNoticeService;
import com.huacainfo.ace.society.service.AuditRecordService;
import com.huacainfo.ace.society.service.SocietyOrgInfoService;
import com.huacainfo.ace.society.vo.OrgAdminVo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoQVo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("societyOrgInfoService")
/**
 * @author: Arvin
 * @version: 2018-09-12
 * @Description: TODO(社会组织信息)
 */
public class SocietyOrgInfoServiceImpl implements SocietyOrgInfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegDao regDao;
    @Autowired
    private PersonInfoDao personInfoDao;
    @Autowired
    private OrgAdminDao orgAdminDao;
    @Autowired
    private SocietyOrgInfoDao societyOrgInfoDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private AuditRecordService auditRecordService;
    @Autowired
    private AuditNoticeService auditNoticeService;
    @Autowired
    private UsersService usersService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(社会组织信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SocietyOrgInfoVo>
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public PageResult<SocietyOrgInfoVo> findSocietyOrgInfoList(SocietyOrgInfoQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<SocietyOrgInfoVo> rst = new PageResult<>();
        List<SocietyOrgInfoVo> list = societyOrgInfoDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = societyOrgInfoDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSocietyOrgInfo
     * @Description: TODO(添加社会组织信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public MessageResponse insertSocietyOrgInfo(SocietyOrgInfo o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getOrgName())) {
            return new MessageResponse(1, "组织名称不能为空！");
        }


        o.setId(StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId());
        int temp = this.societyOrgInfoDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "组织信息名称重复！");
        }

        o.setCreateDate(new Date());
        o.setStatus("2");//默认自动送审
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        societyOrgInfoDao.insert(o);
        dataBaseLogService.log("添加组织信息", "组织信息", "", o.getId(), o.getId(), userProp);
        //自动送审
        sendAudit(o);

        return new MessageResponse(0, "添加组织信息完成！");
    }

    public ResultResponse sendAudit(SocietyOrgInfo obj) {
        if (null == obj) {
            return new ResultResponse(ResultCode.FAIL, "数据记录丢失");
        }
        //提交审核
        auditRecordService.submit(GUIDUtil.getGUID(), BisType.SOCIETY_ORG_INFO, obj.getId(), obj.getId());

        return new ResultResponse(ResultCode.SUCCESS, "送审成功");
    }

    /**
     * @throws
     * @Title:updateSocietyOrgInfo
     * @Description: TODO(更新社会组织信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public MessageResponse updateSocietyOrgInfo(SocietyOrgInfo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOrgName())) {
            return new MessageResponse(1, "组织名称不能为空！");
        }

        SocietyOrgInfo orgInfo = societyOrgInfoDao.selectByPrimaryKey(o.getId());
        orgInfo.setOrgName(o.getOrgName());
        orgInfo.setOrgAddr(o.getOrgAddr());
        orgInfo.setContactPerson(o.getContactPerson());
        orgInfo.setContactPhone(o.getContactPhone());
        orgInfo.setRemark(o.getRemark());
        orgInfo.setLastModifyDate(new Date());
        orgInfo.setLastModifyUserName(userProp.getName());
        orgInfo.setLastModifyUserId(userProp.getUserId());
        this.societyOrgInfoDao.updateByPrimaryKey(orgInfo);
        this.dataBaseLogService.log("变更社会组织信息",
                "社会组织信息", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更社会组织信息完成！");
    }

    /**
     * @throws
     * @Title:selectSocietyOrgInfoByPrimaryKey
     * @Description: TODO(获取社会组织信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SocietyOrgInfo>
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public SingleResult<SocietyOrgInfoVo> selectSocietyOrgInfoByPrimaryKey(String id) throws Exception {
        SingleResult<SocietyOrgInfoVo> rst = new SingleResult<>();
        rst.setValue(this.societyOrgInfoDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSocietyOrgInfoBySocietyOrgInfoId
     * @Description: TODO(删除社会组织信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public MessageResponse deleteSocietyOrgInfoBySocietyOrgInfoId(String id, UserProp userProp) throws
            Exception {
        this.societyOrgInfoDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除社会组织信息", "社会组织信息",
                String.valueOf(id),
                String.valueOf(id), "社会组织信息", userProp);
        return new MessageResponse(0, "社会组织信息删除完成！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核社会组织信息)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-12
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {
        SocietyOrgInfoVo obj = societyOrgInfoDao.selectVoByPrimaryKey(id);
        if (obj == null) {
            return new MessageResponse(ResultCode.FAIL, "组织信息丢失");

        }
        //更改审核记录
        MessageResponse auditRs = auditRecordService.audit(BisType.SOCIETY_ORG_INFO, obj.getId(),
                obj.getId(), rst, remark, userProp);
        if (ResultCode.FAIL == auditRs.getStatus()) {
            return auditRs;
        }

        obj.setStatus(rst);
        obj.setLastModifyDate(DateUtil.getNowDate());
        obj.setLastModifyUserId(userProp.getUserId());
        obj.setLastModifyUserName(userProp.getName());
        societyOrgInfoDao.updateByPrimaryKeySelective(obj);

        //发送微信公众号模板消息
        sendToCustomer(obj, rst, remark);

        dataBaseLogService.log("审核社会组织信息", "社会组织信息", id, id, "社会组织信息", userProp);
        return new MessageResponse(0, "审核成功！");
    }

    private void sendToCustomer(SocietyOrgInfoVo obj, String rst, String remark) {
        try {
//            注册审核通知
//            XXX，注册审核通过/驳回，[机构名称不规范].
//            芙蓉街道XXX系统
//            2018-11-23 14：28
            StringBuffer content = new StringBuffer();
            content.append("注册审核通知\n")
                    .append(obj.getOrgName())
                    .append("，注册审核")
                    .append((AuditState.PASS.equals(rst) ? "通过" : "驳回"));
            if (StringUtil.isNotEmpty(remark)) {
                content.append("，[" + remark + "].\n");
            } else {
                content.append(".\n");
            }
            content.append("芙蓉街道智慧服务社区系统\n")
                    .append(DateUtil.getNow());

            auditNoticeService.sendToCustomer(obj.getAdminId(), content.toString());
        } catch (Exception e) {
            logger.error("[society]组织审核消息发送异常[id={}]：{}", obj.getId(), e);
        }
    }

    @Override
    public List<SocietyOrgInfoVo> findList(SocietyOrgInfoQVo condition, int start, int limit, String orderBy) {
        List<SocietyOrgInfoVo> list = societyOrgInfoDao.findList(condition, start, limit, orderBy);

        return list;
    }

    /**
     * 新组织信息创建
     *
     * @param params 表单参数
     * @return ResultResponse
     */
    @Override
    public ResultResponse newOrgInfo(String crtUserId, SocietyOrgInfoVo params) throws Exception {
        //检查个人信息是否存在
        PersonInfo person = personInfoDao.selectByPrimaryKey(crtUserId);
        if (person == null) {
            return new ResultResponse(ResultCode.FAIL, "用户信息不存在");
        }
        //添加组织信息
        UserProp u = new UserProp();
        u.setUserId("system");
        u.setName("system");
        String orgId = GUIDUtil.getGUID();
        params.setId(orgId);
        //补增portal.department信息
        ResultResponse rsDept = insertDeptInfo(params);
        if (rsDept.getStatus() == ResultCode.FAIL) {
            return new ResultResponse(ResultCode.FAIL, "部门信息新增失败");
        }
        //更新portal.users.department_id信息
        ResultResponse usrDept = updateUsersDeptId(crtUserId, orgId);
        if (rsDept.getStatus() == ResultCode.FAIL) {
            throw new CustomException(usrDept.getInfo());
        }
        //入库组织信息
        MessageResponse ms = insertSocietyOrgInfo(params, u);
        if (ms.getStatus() == ResultCode.SUCCESS) {
            //添加组织管理员列表 --默认创建者即为管理员
            insertOrgAdmin(crtUserId, orgId);
            //通知管理员去审核
            sendToAdmin(params);
        }

        return new ResultResponse(ms);
    }

    private ResultResponse updateUsersDeptId(String crtUserId, String orgId) throws Exception {
        Users users = usersService.selectUsersByPrimaryKey(crtUserId).getValue();
        if (users == null) {
            return new ResultResponse(ResultCode.FAIL, "个人账户不存在");
        }

        users.setDepartmentId(orgId);
        int i = regDao.updateUsersDeptId(users);
        if (i == 1) {
            return new ResultResponse(ResultCode.SUCCESS, "个人账户更新成功");
        }
        return new ResultResponse(ResultCode.FAIL, "个人账户更新失败");
    }

    private ResultResponse insertDeptInfo(SocietyOrgInfoVo params) {
        Department d = new Department();
        d.setDepartmentId(params.getId());
        d.setParentDepartmentId("0002");
        d.setDepartmentName(params.getOrgName());
        d.setRegDate(new Date());
        d.setCreateTime(new Date());
        d.setStatus("1");
        d.setSyid("society");

        int i = regDao.insertDepartmentWithDepId(d);
        if (i == 1) {
            return new ResultResponse(ResultCode.SUCCESS, "新增成功");
        }

        return new ResultResponse(ResultCode.FAIL, "新增失败");
    }

    private void sendToAdmin(SocietyOrgInfoVo params) {
        try {
//            注册审核通知
//            XXX注册成功，请及时审核.
//                    芙蓉街道XXX系统
//            2018 - 11 - 23 14：28
            StringBuffer content = new StringBuffer();
            content.append("注册审核通知\n")
                    .append(params.getOrgName())
                    .append(" 注册成功，请及时审核.\n")
                    .append("芙蓉街道智慧服务社区系统\n")
                    .append(DateUtil.getNow());

            auditNoticeService.sendToAdmin(content.toString());
        } catch (Exception e) {
            logger.error("[society]组织送审消息发送异常[orgId={}]：{}", params.getId(), e);
        }
    }

    private int insertOrgAdmin(String userId, String orgId) {
        OrgAdmin admin = new OrgAdmin();
        admin.setId(GUIDUtil.getGUID());
        admin.setUserId(userId);
        admin.setOrgId(orgId);
        admin.setCreateDate(DateUtil.getNowDate());
        return orgAdminDao.insert(admin);
    }

    /**
     * 查询组织负责人
     *
     * @param orgId 组织ID
     * @return SingleResult<OrgAdminVo>
     * @throws Exception
     */
    @Override
    public SingleResult<OrgAdminVo> findOrgAdmin(String orgId) throws Exception {
        SingleResult<OrgAdminVo> rst = new SingleResult<>();
        rst.setValue(orgAdminDao.findByOrgId(orgId));

        return rst;
    }

    /**
     * 移除组织负责人
     *
     * @param orgId 组织ID
     * @return SingleResult<OrgAdminVo>
     * @throws Exception
     */
    @Override
    public MessageResponse removeAdmin(String orgId) {
        int i = orgAdminDao.deleteByOrgId(orgId);
        if (i == 1) {
            return new MessageResponse(ResultCode.SUCCESS, "移除成功");
        }
        return new MessageResponse(ResultCode.FAIL, "移除失败");
    }

    /**
     * 添加/更换 组织负责人
     *
     * @param orgId  组织ID
     * @param userId
     * @return SingleResult<OrgAdminVo>
     * @throws Exception
     */
    @Override
    public MessageResponse addAdmin(String orgId, String userId) throws Exception {
        OrgAdmin params = new OrgAdmin();
        params.setOrgId(orgId);
        params.setUserId(userId);
        int count = orgAdminDao.isExist(params);
        if (count != 0) {
            return new MessageResponse(ResultCode.FAIL, "此人已作为其他组织负责人，请重新选择");
        }
        //只保留一个组织负责人
        OrgAdminVo o = orgAdminDao.findByOrgId(orgId);
        if (o != null) {
            orgAdminDao.deleteByOrgId(orgId);
        }
        //
        int i = insertOrgAdmin(userId, orgId);
        if (i == 1) {
            //更新个人账户部门信息
            updateUsersDeptId(userId, orgId);

            return new MessageResponse(ResultCode.SUCCESS, "添加/更换成功");
        }
        return new MessageResponse(ResultCode.FAIL, "添加/更换失败");
    }

    /**
     * 查询 待选负责人列表
     *
     * @param keyword 查询关键字
     * @return SingleResult<OrgAdminVo>
     * @throws Exception
     */
    @Override
    public Map<String, Object> findAdminList(String keyword) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = orgAdminDao.findAdminList(keyword);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

}