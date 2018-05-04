package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.model.FopMember;
import com.huacainfo.ace.fop.service.FopCompanyService;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopMemberService;
import com.huacainfo.ace.fop.service.FopPersonService;
import com.huacainfo.ace.fop.vo.FopCompanyQVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.DepartmentService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("fopCompanyService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public class FopCompanyServiceImpl implements FopCompanyService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private FopCompanyDao fopCompanyDao;
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

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopCompanyVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopCompanyVo> findFopCompanyList(FopCompanyQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<FopCompanyVo> rst = new PageResult<FopCompanyVo>();
        List<FopCompanyVo> list = this.fopCompanyDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopCompanyDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopCompany
     * @Description: TODO(添加企业管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopCompany(FopCompanyVo o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(ResultCode.FAIL, "企业全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPersonId())) {
            return new MessageResponse(ResultCode.FAIL, "企业法人不能为空！");
        }
        if (CommonUtils.isBlank(o.getLpMobile())) {
            return new MessageResponse(ResultCode.FAIL, "企业法人联系方式不能为空!");
        }
        int temp = this.fopCompanyDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(ResultCode.FAIL, "企业名称重复！");
        }
        //初始化系统用户
        ResultResponse rs1 = initSysUser(o, userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            return new MessageResponse(ResultCode.FAIL, rs1.getInfo());
        }
        //构建法人信息
        ResultResponse rs2 = fopPersonService.insertPerson(o, userProp);
        if (ResultCode.FAIL == rs2.getStatus()) {
            return new MessageResponse(ResultCode.FAIL, rs2.getInfo());
        }
        //入库企业信息
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopCompanyDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);

        //自动审核会员处理
        MessageResponse rs3 = memberJoinAutoAudit(o, userProp);
        if (ResultCode.FAIL == rs3.getStatus()) {
            return rs3;
        }

        return new MessageResponse(0, "添加企业管理完成！");
    }

    /**
     * 加入会员
     *
     * @param o
     * @param userProp
     * @return
     * @throws Exception
     */
    private MessageResponse memberJoinAutoAudit(FopCompanyVo o, UserProp userProp) throws Exception {
        //审核流程记录
        MessageResponse rs = fopFlowRecordService.memberJoinAutoAudit(FlowType.MEMBER_JOIN_COMPANY, o, userProp);
        if (ResultCode.FAIL == rs.getStatus()) {
            return rs;
        }
        //
        FopMember member = new FopMember();
        member.setRelationId(o.getId());
        member.setRelationType("0");//0-企业会员 1-团体会员
        member.setMermberName(o.getFullName());
        member.setMemberCode(System.currentTimeMillis() + "");
        member.setMemberLevel("0");
        member.setJoinDate(DateUtil.getNowDate());
        String validDate = DateUtil.getDate("year", DateUtil.getNow(), 1, "");
        member.setValidDate(DateUtil.format(validDate, DateUtil.DEFAULT_DATE_TIME_REGEX));

        return fopMemberService.insertFopMember(member, userProp);
    }

    /**
     * 初始化系统用户
     *
     * @param o
     * @param userProp
     * @return
     */
    private ResultResponse initSysUser(FopCompanyVo o, UserProp userProp) throws Exception {
        //portal.department
        Department department = new Department();
        department.setDepartmentId(GUIDUtil.getGUID());
        department.setDepartmentName(o.getFullName());
        department.setSyid(userProp.getActiveSyId());
        department.setAreaCode(userProp.getAreaCode());
        MessageResponse rs1 = departmentService.insertDepartment(department, userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            return new ResultResponse(rs1);
        }
        //portal.users
        Users users = new Users();
        users.setDepartmentId(department.getDepartmentId());
        users.setAccount(o.getLpMobile());
        users.setPassword("123456");
        users.setSex("1");
        users.setName(o.getFullName());
        ResultResponse rs2 = usersService.insertUsersRecord(users, userProp, "市工商联 -- 企业注册");
        if (ResultCode.FAIL == rs2.getStatus()) {
            return rs2;
        }
        //分配用户角色
        List<Map<String, Object>> roleList = fopCompanyDao.selectRoleList(userProp.getActiveSyId(), new String[]{"4"});
        if (CollectionUtils.isEmpty(roleList)) {
            return new ResultResponse(ResultCode.FAIL, "未预设用户角色，用户赋权失败");
        }
        Map<String, Object> role = roleList.get(0);
        String[] roleId = new String[]{(String) role.get("role_id")};
        Users users1 = (Users) rs2.getData();
        MessageResponse rs3 = usersService.insertUsersRole(users1.getId(), roleId, userProp);
        if (ResultCode.FAIL == rs3.getStatus()) {
            return new ResultResponse(rs3);
        }

        return new ResultResponse(ResultCode.SUCCESS, "初始化系统用户成功", department);
    }

    /**
     * @throws
     * @Title:updateFopCompany
     * @Description: 更新企业管理
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopCompany(FopCompanyVo o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(1, "企业全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPersonId())) {
            return new MessageResponse(1, "企业法人代表不能为空！");
        }

        int temp = this.fopCompanyDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(ResultCode.FAIL, "企业名称重复！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopCompanyDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更企业管理完成！");
    }

    /**
     * @throws
     * @Title:selectFopCompanyByPrimaryKey
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopCompany>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopCompanyVo> selectFopCompanyByPrimaryKey(String id) throws Exception {
        SingleResult<FopCompanyVo> rst = new SingleResult<>();
        rst.setValue(this.fopCompanyDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopCompanyByFopCompanyId
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopCompanyByFopCompanyId(String id,
                                                          UserProp userProp) throws Exception {

        FopCompany company = fopCompanyDao.selectByPrimaryKey(id);
        if (null == company) {
            return new MessageResponse(ResultCode.FAIL, "无效企业编号");
        }
        company.setStatus("-1");
        company.setLastModifyDate(DateUtil.getNowDate());
        company.setLastModifyUserId(userProp.getUserId());
        company.setLastModifyUserName(userProp.getName());
        fopCompanyDao.updateByPrimaryKeySelective(company);


        this.dataBaseLogService.log("删除企业管理", "企业管理", String.valueOf(id),
                String.valueOf(id), "企业管理", userProp);
        return new MessageResponse(0, "企业管理删除完成");
    }
}
