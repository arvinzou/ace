package com.huacainfo.ace.fop.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.redis.AspireRedisTemplate;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.dao.ComDataImportDao;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.dao.SysAccountDao;
import com.huacainfo.ace.fop.model.ComDataImport;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.service.FopCompanyService;
import com.huacainfo.ace.fop.service.SysAccountService;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.UserCfg;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.ResourcesService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.DepartmentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service("sysAccountService")
/**
 * Created by HuaCai008 on 2018/6/4.
 */
public class SysAccountServiceImpl implements SysAccountService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysAccountDao sysAccountDao;

    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private ResourcesService resourceService;

    @Autowired
    private FopCompanyDao fopCompanyDao;
    @Autowired
    private FopAssociationDao fopAssociationDao;
    @Autowired
    private UsersService usersService;
    @Autowired
    private FopCompanyService fopCompanyService;
    @Autowired
    private ComDataImportDao comDataImportDao;

    /**
     * 新增部门
     *
     * @param o
     * @param userProp
     * @return
     */
    @Override
    public MessageResponse insertDepartment(Department o, UserProp userProp) {
        if (CommonUtils.isBlank(o.getParentDepartmentId())) {
            return new MessageResponse(1, "所属协会不能为空！");
        }
        if (CommonUtils.isBlank(o.getDepartmentName())) {
            return new MessageResponse(1, "企业名称不能为空！");
        }

        o.setRegDate(new Date());
        o.setCreateTime(new Date());
        o.setStatus("1");
        o.setSyid(userProp.getActiveSyId());
        sysAccountDao.insertDepartmentWithDepId(o);
        dataBaseLogService.log("添加新机构", "机构",
                "企业名称：" + o.getDepartmentName()
                        + "营业执照号：" + o.getBussLicenseNo()
                        + ",企业编号：" + o.getDepartmentId(), "",
                o.getDepartmentName(), userProp);
        return new MessageResponse(0, "添加新机构完成！");
    }

    /**
     * 新增系统用户
     *
     * @param users
     * @param userProp
     * @param logTag
     * @return
     */
    @Override
    public ResultResponse insertUsersRecord(Users users, UserProp userProp, String logTag) throws Exception {
        if (CommonUtils.isBlank(users.getDepartmentId())) {
            return new ResultResponse(1, "归属部门不能为空!");
        }
        if (CommonUtils.isBlank(users.getAccount())) {

            return new ResultResponse(1, "账户不能为空!");
        }
        if (sysAccountDao.isExitUsersAccount(users.getAccount()) > 0) {
            return new ResultResponse(1, "账户已存在!");
        }
        if (CommonUtils.isBlank(users.getPassword())) {

            return new ResultResponse(1, "密码不能为空!");
        }
        if (CommonUtils.isBlank(users.getSex())) {

            return new ResultResponse(1, "性别不能为空!");
        }
        DepartmentVo dept = sysAccountDao.selectDepartmentVoByPrimaryKey(users.getDepartmentId());
        if (CommonUtils.isBlank(dept)) {
            return new ResultResponse(1, "归属部门不存在!");
        }
        if (CommonUtils.isBlank(users.getName())) {
            return new ResultResponse(1, "姓名不能为空!");
        }
        users.setAreaCode(dept.getAreaCode());
        users.setStauts("1");
        users.setPassword(CommonUtils.getMd5(users.getPassword()));
        String id = String.valueOf(System.currentTimeMillis());
        users.setUserId(id);
        users.setCreateTime(new Date());
        users.setCurSyid(userProp.getActiveSyId());
        sysAccountDao.insertUsers(users);
        dataBaseLogService.log("员工添加成功", logTag, "",
                "账号:" + users.getAccount() + ",姓名:" + users.getName(), "01", userProp);

        return new ResultResponse(0, "添加员工户完成！", users);
    }

    /**
     * 给用户配置分配角色
     *
     * @param userId
     * @param roleId
     * @param userProp
     * @return
     * @throws Exception
     */
    @Override
    public MessageResponse insertUsersRole(String userId, String[] roleId, UserProp userProp) throws Exception {
        sysAccountDao.insertUsersRole(userId, roleId);
        List<Map<String, String>> list = resourceService.loadResourceDefine();
        AspireRedisTemplate redisTemplateString = (AspireRedisTemplate) SpringUtils
                .getBean("redisTemplateString");
        WebUtils.flushRoleResourceCache(redisTemplateString, list);
        this.dataBaseLogService.log("员工分配角色", "分配角色", "", "", userId, userProp);
        return new MessageResponse(0, "角色分配完成！");
    }

    /**
     * 给用户设定个性化配置
     *
     * @param list
     * @param userProp
     * @return
     * @throws Exception
     */
    @Override
    public MessageResponse saveOrUpdateUserCfg(List<UserCfg> list, UserProp userProp) throws Exception {
        for (UserCfg o : list) {
            int temp = sysAccountDao.isExitUserCfg(o);
            if (temp > 0) {
                this.sysAccountDao.updateUserCfgByPKey(o);
            } else {
                o.setId(UUID.randomUUID().toString());

                sysAccountDao.insertUserCfg(o);
            }

            dataBaseLogService.log("变更个性化配置", "个性化配置", "", o.getCfgKey(), o.getCfgKey(), userProp);
        }
        return new MessageResponse(0, "变更个性化配置完成！");
    }

    /**
     * 初始化系统用户
     *
     * @param password
     * @param userProp
     * @return
     */
    @Override
    public ResultResponse initSysUser(String deptName, String account, String password,
                                      String logTag, UserProp userProp) throws Exception {
        //portal.department
        Department department = new Department();
        department.setDepartmentId(GUIDUtil.getGUID());
        department.setDepartmentName(deptName);
        department.setSyid(userProp.getActiveSyId());
        department.setAreaCode(userProp.getAreaCode());
        //市工商联
        department.setParentDepartmentId("0010006");
        MessageResponse rs1 = insertDepartment(department, userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            throw new CustomException(rs1.getErrorMessage());
        }
        //portal.users
        Users initUser = new Users();
        initUser.setDepartmentId(department.getDepartmentId());
        initUser.setAccount(account);
        initUser.setMobile(account);
        initUser.setName(deptName);
        initUser.setPassword(password);
        initUser.setSex("1");
        initUser.setUserLevel("1");
        ResultResponse rs2 = insertUsersRecord(initUser, userProp, logTag);
        if (ResultCode.FAIL == rs2.getStatus()) {
            throw new CustomException(rs2.getInfo());
        }
        //分配用户角色 默认分配非会员权限，注册申请成功后，分配会员权限
        Users users = (Users) rs2.getData();
        ResultResponse rs3 = dispatchRoleRight(users.getUserId(), userProp, new String[]{"5"});
        if (ResultCode.FAIL == rs3.getStatus()) {
            throw new CustomException(rs3.getInfo());
        }

        //插入登录后，默认跳转页配置
        List<UserCfg> cfgList = new ArrayList<>();
        UserCfg userCfg = new UserCfg();
        userCfg.setUserId(users.getUserId());
        userCfg.setCfgKey("portalType");
        userCfg.setCfgValue("5");//非会员权限
        cfgList.add(userCfg);
        saveOrUpdateUserCfg(cfgList, userProp);

        return new ResultResponse(ResultCode.SUCCESS, "初始化系统用户成功", department);
    }

    @Override
    public String getAccount(String relationType, String relationId) {
        if (FopConstant.COMPANY.equals(relationType)) {
            FopCompanyVo company = fopCompanyDao.selectVoByPrimaryKey(relationId);
            return null == company ? "" : company.getLpMobile();
        } else {
            FopAssociation association = fopAssociationDao.selectByPrimaryKey(relationId);

            return null == association ? "" : association.getPhoneNumber();
        }
    }

    @Override
    public ResultResponse destoryAccount(String account) {
        Users users = usersService.selectByAccount(account);
        if (null == users) {
            return new ResultResponse(ResultCode.FAIL, "无当前账号信息：" + account);
        }

        int i1 = sysAccountDao.deleteUserCfg(users.getUserId());
        int i2 = sysAccountDao.deleteUsersRole(users.getUserId());
        int i3 = sysAccountDao.deleteFopCompany(users.getDepartmentId());
        int i4 = sysAccountDao.deleteFopPerson(account);
        int i5 = sysAccountDao.deleteUsers(account);
        int i6 = sysAccountDao.deleteDepartment(users.getDepartmentId());

        return new ResultResponse(ResultCode.SUCCESS,
                "删除成功：" + i1 + "|" + i2 + "|" + i3 + "|" + i4 + "|" + i5 + "|" + i6);
    }

    @Override
    public Map<String, Object> getAccountInfo(String relationId, String relationType) {
        Map<String, Object> rtn = new HashMap<>();
        if (FopConstant.COMPANY.equals(relationType)) {
            FopCompanyVo company = fopCompanyDao.selectVoByPrimaryKey(relationId);
            rtn.put("account", null == company ? "" : company.getLpMobile());
            rtn.put("name", null == company ? "" : company.getFullName());
        } else if (FopConstant.ASSOCIATION.equals(relationType)) {
            FopAssociation association = fopAssociationDao.selectByPrimaryKey(relationId);

            rtn.put("account", null == association ? "" : association.getPhoneNumber());
            rtn.put("name", null == association ? "" : association.getFullName());
        } else {
            rtn.put("account", "常德市工商联");
            rtn.put("name", "常德市工商联");
        }

        return rtn;
    }

    @Override
    public ResultResponse destroyAll() {
//        List<Users> list= sysAccountDao.findAll();
        return null;
    }

    private ResultResponse dispatchRoleRight(String userId, UserProp userProp, String[] roleTypes) throws Exception {
        List<Map<String, Object>> roleList = sysAccountDao.selectRoleList(userProp.getActiveSyId(), roleTypes);
        if (CollectionUtils.isEmpty(roleList)) {
            return new ResultResponse(ResultCode.FAIL, "未预设用户角色，用户赋权失败");
        }
        Map<String, Object> role = roleList.get(0);
        String[] roleId = new String[]{(String) role.get("role_id")};
        MessageResponse rs3 = insertUsersRole(userId, roleId, userProp);

        return new ResultResponse(rs3);
    }

    private Date getDate(String dateStr) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }

        String[] array = dateStr.split("/");
        String year = array[0];
        String month = array[1].length() < 2 ? "0" + array[1] : array[1];
        String day = array[2].length() < 2 ? "0" + array[2] : array[2];

        String date = year + "-" + month + "-" + day + " 00:00:00";

        return DateUtil.toDate(date);
    }

    private Integer getInteger(String str) {

        return StringUtil.isEmpty(str) ? 0 : Integer.parseInt(str);
    }

    private BigDecimal getBigDecimal(String str) {

        return StringUtil.isEmpty(str) ? BigDecimal.ZERO : new BigDecimal(str).setScale(2);
    }

    @Override
    public ResultResponse dataImport() throws Exception {
        List<ComDataImport> list = comDataImportDao.findList();
        for (ComDataImport data : list) {
            generateCompanyInfo(data);
        }

        return new ResultResponse(ResultCode.SUCCESS, "导入成功");

    }

    private void generateCompanyInfo(ComDataImport data) throws Exception {
        String creditCode = data.getCreditCode();
        FopCompanyVo company = fopCompanyDao.findByCreditCode(creditCode);
        FopCompanyVo params = parseFopCompany(data, company);

        UserProp userProp = new UserProp();
        userProp.setName("system");
        userProp.setUserId("system");
        userProp.setActiveSyId("fop");
        MessageResponse rs;
        if (null == company) {
            rs = fopCompanyService.insertFopCompany(params, userProp);
        } else {
            rs = fopCompanyService.updateFopCompany(params, userProp);
        }
        if (rs.getStatus() == ResultCode.FAIL) {
            throw new CustomException(rs.getErrorMessage());
        }
    }


    private FopCompanyVo parseFopCompany(ComDataImport data, FopCompanyVo company) {
        if (null == company) {
            company = new FopCompanyVo();
        }
        company.setCreditCode(data.getCreditCode());
        company.setCompanyType("0");
        company.setFullName(data.getFullName());
        company.setCompanyProperty(data.getCompanyProperty());
        company.setBelongTo(data.getBelongTo());
        company.setCrewSize(getInteger(data.getCrewSize()));
        company.setAreaCode(data.getAreaCode());
        company.setEstablishDate(DateUtil.toDate(data.getEstablishDate() + " 00:00:00"));
        company.setRegisteredCapital(getBigDecimal(data.getRegisteredCapital()));
        company.setFixedAssets(getBigDecimal(data.getFixedAssets()));
        company.setWorkingCapital(getBigDecimal(data.getWorkingCapital()));
        company.setOwnSpace(getBigDecimal(data.getOwnSpace()));
        company.setTenancySpace(getBigDecimal(data.getTenancySpace()));
        company.setAddress(data.getAddress());
        company.setPostcode(data.getPostcode());
        company.setFax(data.getFax());
        company.setMajorVariety(data.getMajorVariety());
        company.setLaborContractNum(getInteger(data.getLaborContractNum()));
        company.setThirdLaborRelation(data.getThirdLaborRelation());
        company.setSocialInsuranceAddr(data.getSocialInsuranceAddr());
        company.setSocialInsuranceNum(data.getSocialInsuranceNum());
        //法人信息
        company.setRealName(data.getRealName());
        company.setLpMobile(data.getLpMobile());
        company.setLpSex(data.getLpSex());
        company.setLpBirthDt(getDate(data.getLpBirthDt()));
        company.setLpNativePlace(data.getLpNativePlace());
        company.setLpNationality(data.getLpNationality());
        company.setLpPolitical(data.getLpPolitical());
//        company.setLpRecruitmentDate(DateUtil.toDate(data.getLpRecruitmentDate()));
        company.setLpEducation(data.getLpEducation());
        company.setLpSkillJobTitle(data.getLpSkillJobTitle());
        company.setLpDeptPost(data.getLpDeptPost());
        company.setLpIdentityCard(data.getLpIdentityCard());
        company.setLpSocietyPost(data.getLpSocietyPost());
        company.setLpResume(data.getLpResume());
        company.setLpAchievement(data.getLpAchievement());
        //党组织
        company.setPartyCategory(data.getPartyCategory());
        company.setPartyCrtDt(DateUtil.toDate(data.getPartyCrtDt()));
        company.setPartyPeoples(getInteger(data.getPartyPeoples()));
        company.setPartyDutyMan(data.getPartyDutyMan());
        company.setPartyPhone(data.getPartyPhone());
        //工会组织建立情况
        company.setUnionCrtDt(DateUtil.toDate(data.getUnionCrtDt()));
        company.setUnionDutyMan(data.getUnionDutyMan());
        company.setUnionPhone(data.getUnionPhone());
        //对社会公益事业做过何种贡献
        company.setReemployContribution(data.getReemployContribution());
        company.setEducationContribution(data.getEducationContribution());
        company.setHelpPoorContribution(data.getHelpPoorContribution());
        company.setOtherContribution(data.getOtherContribution());

        return company;
    }
}
