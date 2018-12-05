package com.huacainfo.ace.fop.service.impl;


import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.RandomValidateCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.ValidateUtils;
import com.huacainfo.ace.fop.common.api.DataSwapperApi;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.common.constant.PayType;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.dao.FopCompanyAnnexDao;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.dao.FopCompanyOrgDao;
import com.huacainfo.ace.fop.model.*;
import com.huacainfo.ace.fop.service.*;
import com.huacainfo.ace.fop.vo.*;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("fopCompanyService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(企业管理)
 */
public class FopCompanyServiceImpl implements FopCompanyService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FopCompanyDao fopCompanyDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private FopPersonService fopPersonService;

    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    @Autowired
    private FopPayRecordService fopPayRecordService;


    @Autowired
    private FopCompanyOrgService fopCompanyOrgService;

    @Autowired
    private FopCompanyContributionService fopCompanyContributionService;

    @Autowired
    private SysAccountService sysAccountService;
    @Autowired
    private FopAssociationDao fopAssociationDao;

    @Autowired
    private UsersService usersService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private FopCompanyOrgDao fopCompanyOrgDao;
    @Autowired
    private FopMemberService fopMemberService;
    @Autowired
    private FopCompanyAnnexDao fopCompanyAnnexDao;

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


    @Override
    public ResultResponse findCompanyList(FopCompanyQVo condition, int page, int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        condition.setStatus("2");
        if (!condition.getIsCompany().equals("2")) {
            if (condition.getIsCompany().equals("1")) {
                condition.setCompanyType("w");
            } else {
                condition.setCompanyType(condition.getIsCompany());
            }
            map.put("list", this.fopCompanyDao.findList(condition, (page - 1) * limit, limit, orderBy));
            map.put("total", this.fopCompanyDao.findCount(condition));
            ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "企业列表", map);
            return rst;
        }
        FopAssociationQVo fopAssociationQVo = new FopAssociationQVo();
        fopAssociationQVo.setFullName(condition.getFullName());
        fopAssociationQVo.setAreaCode(condition.getAreaCode());
        fopAssociationQVo.setStatus("2");
        List<FopAssociationVo> list = fopAssociationDao.findList(fopAssociationQVo, (page - 1) * limit, limit, orderBy);
        List<FopCompanyVo> clist = new ArrayList<FopCompanyVo>();
        for (FopAssociationVo item : list) {
            FopCompanyVo f = new FopCompanyVo();
            f.setFullName(item.getFullName());
//            f.setRealName(item.getRealName());
            f.setAreaCodeName(item.getAreaCodeName());
            f.setAddress(item.getAddress());
            f.setCompanyProperty("-");
            clist.add(f);
        }
        map.put("list", clist);
        map.put("total", this.fopAssociationDao.findCount(fopAssociationQVo));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "团体列表", map);
        return rst;

    }

    @Override
    public ResultResponse findCompanyGisList() throws Exception {
        List<FopCompanyVo> datas = this.fopCompanyDao.findGisList();
        String[] value = new String[4];
        String rst = "[";
        Map<String, Object> itmp = new HashMap<String, Object>();
        for (FopCompanyVo data : datas) {
            itmp.put("name", data.getFullName());
            if (null == data.getLatitude()) {
                continue;
            }
            value[0] = data.getLongitude().toString();
            value[1] = data.getLatitude().toString();
            value[2] = data.getId();
            value[3] = data.getAddress();
            itmp.put("value", value);
            rst = rst + JSON.toJSONString(itmp) + ",";
        }
        rst = rst + "{}]";
        return new ResultResponse(ResultCode.SUCCESS, "企业列表", rst);
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
    public MessageResponse insertFopCompany(FopCompanyVo o, UserProp userProp) throws Exception {
        if (!CommonUtils.isValidMobile(o.getLpMobile())) {
            return new MessageResponse(ResultCode.FAIL, "不是手机号码");
        }


        //设置主键标识
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(ResultCode.FAIL, "企业全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(ResultCode.FAIL, "企业法人姓名!");
        }
        if (CommonUtils.isBlank(o.getLpMobile())) {
            return new MessageResponse(ResultCode.FAIL, "企业法人联系方式不能为空!");
        }
        int temp = this.fopCompanyDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(ResultCode.FAIL, "企业名称重复！");
        }
        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }
        //初始化系统用户
        String password = "123456";//RandomValidateCode.CreateRadom(8, 2)
        ResultResponse rs1 = sysAccountService.initSysUser(o.getFullName(), o.getLpMobile(),
                password, "市工商联 -- 企业注册", userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            return new MessageResponse(ResultCode.FAIL, rs1.getInfo());
        }
        Department department = (Department) rs1.getData();
        //构建法人信息
        ResultResponse rs2 = fopPersonService.insertPerson(o, userProp);
        if (ResultCode.FAIL == rs2.getStatus()) {
            throw new CustomException(rs2.getInfo());
        }
        FopPerson person = (FopPerson) rs2.getData();
        //公司组织信息
        insertOrUpdateCompanyOrgInfo(o, userProp);
        //对社会公益事业做过何种贡献信息
        insertOrUpdateContribution(o, userProp);
        //入库企业信息
        o.setPersonId(person.getId());
        o.setCompanyType(CommonUtils.isBlank(o.getCompanyType()) ? "0" : o.getCompanyType());
        o.setDepartmentId(department.getDepartmentId());
        o.setCreateDate(new Date());
//        String companyType = o.getCompanyType();
//        String status = "3".equals(companyType) ? "2" : "1";
        o.setStatus("2");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopCompanyDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        //自动提交会员申请流程
//        if (!"3".equals(companyType)) {//银行添加无需提交审核
//            MessageResponse rs3 = fopFlowRecordService.submitFlowRecord(GUIDUtil.getGUID(),
//                    FlowType.MEMBER_JOIN_COMPANY, o.getId(), userProp);
//            if (ResultCode.FAIL == rs3.getStatus()) {
//                return rs3;
//            }
//        }

        //注册成功消息推送
//        sendRegisterMessage(o.getLpMobile(), o.getFullName(), password);

        return new MessageResponse(0, "添加企业管理完成！");
    }

    /**
     * 存储 对社会公益事业做过何种贡献信息
     *
     * @param o
     * @param userProp
     */
    private void insertOrUpdateContribution(FopCompanyVo o, UserProp userProp) throws Exception {
        //后台管理端更新逻辑
        if (StringUtil.oneIsNotEmty(o.getReemployContribution(), o.getEducationContribution(),
                o.getHelpPoorContribution(), o.getOtherContribution())) {
            String cid = o.getId();
            List<FopCompanyContribution> contributes = new ArrayList<>();
            FopCompanyContribution c1 = new FopCompanyContribution(cid, "0",
                    "1", "安排下岗职工再就业", o.getReemployContribution(), 1);
            FopCompanyContribution c2 = new FopCompanyContribution(cid, "0",
                    "2", "助学兴教", o.getEducationContribution(), 2);
            FopCompanyContribution c3 = new FopCompanyContribution(cid, "0",
                    "3", "帮困扶贫", o.getHelpPoorContribution(), 3);
            FopCompanyContribution c4 = new FopCompanyContribution(cid, "0",
                    "4", "其他", o.getOtherContribution(), 4);
            contributes.add(c1);
            contributes.add(c2);
            contributes.add(c3);
            contributes.add(c4);

         /*删除贡献*/
            MessageResponse rs3 = fopCompanyContributionService.deleteFopCompanyContributionByCID(cid, userProp);
            if (ResultCode.FAIL == rs3.getStatus()) {
                throw new CustomException(rs3.getErrorMessage());
            }
        /*插入贡献*/
            for (FopCompanyContribution contribute : contributes) {
                contribute.setCompanyId(cid);
                MessageResponse rs4 = fopCompanyContributionService.insertFopCompanyContribution(contribute, userProp);
                if (ResultCode.FAIL == rs4.getStatus()) {
                    throw new CustomException(rs4.getErrorMessage());
                }
            }
        }
    }

    /**
     * 存储公司组织信息
     *
     * @param o
     * @param userProp
     */
    private void insertOrUpdateCompanyOrgInfo(FopCompanyVo o, UserProp userProp) {
        String companyId = o.getId();
        if (StringUtil.oneIsNotEmty(o.getPartyCategory(), o.getPartyDutyMan(), o.getPartyPhone(), o.getPartyPeoples() + "")
                || o.getPartyCrtDt() != null) {
            FopCompanyOrgVo party = fopCompanyOrgDao.findCompayOrgType(companyId, "1");//党组织
            if (null == party) {
                party = new FopCompanyOrgVo();
                party.setId(GUIDUtil.getGUID());
                party.setCompanyId(companyId);
                party.setCompanyOrgType("1");
                party.setOrgName("党组织");
                party.setOrgType(o.getPartyCategory());
                party.setPeopleNum(o.getPartyPeoples());
                party.setEstablishDate(o.getPartyCrtDt());
                party.setDutyPersonName(o.getPartyDutyMan());
                party.setDutyPersonPhone(o.getPartyPhone());
                party.setStatus("1");
                party.setCreateUserId(userProp.getUserId());
                party.setCreateUserName(userProp.getName());
                party.setCreateDate(DateUtil.getNowDate());
                party.setLastModifyDate(DateUtil.getNowDate());
                fopCompanyOrgDao.insertSelective(party);
            } else {
                party.setOrgType(o.getPartyCategory());
                party.setPeopleNum(o.getPartyPeoples());
                party.setEstablishDate(o.getPartyCrtDt());
                party.setDutyPersonName(o.getPartyDutyMan());
                party.setDutyPersonPhone(o.getPartyPhone());
                party.setLastModifyUserId(userProp.getUserId());
                party.setLastModifyUserName(userProp.getName());
                party.setLastModifyDate(DateUtil.getNowDate());
                fopCompanyOrgDao.updateByPrimaryKeySelective(party);
            }
        }
        //工会组织
        if (StringUtil.oneIsNotEmty(o.getUnionDutyMan(), o.getUnionPhone()) || o.getUnionCrtDt() != null) {
            FopCompanyOrgVo union = fopCompanyOrgDao.findCompayOrgType(o.getId(), "2");//工会组织
            if (null == union) {
                union = new FopCompanyOrgVo();
                union.setId(GUIDUtil.getGUID());
                union.setCompanyId(companyId);
                union.setCompanyOrgType("2");
                union.setOrgName("工会组织");
                union.setEstablishDate(o.getUnionCrtDt());
                union.setDutyPersonName(o.getUnionDutyMan());
                union.setDutyPersonPhone(o.getUnionPhone());
                union.setStatus("1");
                union.setCreateUserId(userProp.getUserId());
                union.setCreateUserName(userProp.getName());
                union.setCreateDate(DateUtil.getNowDate());
                union.setLastModifyDate(DateUtil.getNowDate());
                fopCompanyOrgDao.insertSelective(union);
            } else {
                union.setEstablishDate(o.getUnionCrtDt());
                union.setDutyPersonName(o.getUnionDutyMan());
                union.setDutyPersonPhone(o.getUnionPhone());
                union.setLastModifyUserId(userProp.getUserId());
                union.setLastModifyUserName(userProp.getName());
                union.setLastModifyDate(DateUtil.getNowDate());
                fopCompanyOrgDao.updateByPrimaryKeySelective(union);
            }
        }
    }

    @Override
    public MessageResponse insertCompany(String fullName, String lpMobile) throws Exception {
        if (!CommonUtils.isValidMobile(lpMobile)) {
            return new MessageResponse(ResultCode.FAIL, "不是手机号码");
        }
        FopCompanyVo o = new FopCompanyVo();
        o.setFullName(fullName);
        o.setLpMobile(lpMobile);
        o.setCompanyType(FopConstant.COMPANY);
        UserProp userProp = new UserProp();
        o.setLpSex("1");
        userProp.setActiveSyId("fop");
        userProp.setName("工商联管理员");
        userProp.setUserId("1522198886381");
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(ResultCode.FAIL, "企业全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getLpMobile())) {
            return new MessageResponse(ResultCode.FAIL, "企业法人联系方式不能为空!");
        }
        if (CommonUtils.isBlank(o.getCompanyType())) {
            return new MessageResponse(ResultCode.FAIL, "注册会员类型不能为空!");
        }
        int temp = this.fopCompanyDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(ResultCode.FAIL, "企业名称重复！");
        }
        //初始化系统用户
        String password = RandomValidateCode.CreateRadom(8, 2);
        ResultResponse rs1 = sysAccountService.initSysUser(o.getFullName(), o.getLpMobile(), password,
                "市工商联 -- 企业注册", userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            return new MessageResponse(ResultCode.FAIL, rs1.getInfo());
        }
        //构建法人信息
        ResultResponse rs2 = fopPersonService.insertPerson(o, userProp);
        if (ResultCode.FAIL == rs2.getStatus()) {
            throw new CustomException(rs2.getInfo());
        }
        //入库企业信息
        FopPerson fp = (FopPerson) rs2.getData();

        Department department = (Department) rs1.getData();
        o.setDepartmentId(department.getDepartmentId());
//        o.setCompanyType(CommonUtils.isBlank(o.getCompanyType()) ? "0" : o.getCompanyType());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setPersonId(fp.getId());
        this.fopCompanyDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);

        //自动提交会员申请流程
        MessageResponse rs3 = fopFlowRecordService.submitFlowRecord(GUIDUtil.getGUID(),
                FlowType.MEMBER_JOIN_COMPANY, o.getId(), userProp);
        if (ResultCode.FAIL == rs3.getStatus()) {
            return rs3;
        }
        //自动提交缴费记录
        MessageResponse rs4 = submitPayRecord(o, userProp);
        if (ResultCode.FAIL == rs4.getStatus()) {
            return rs4;
        }
        //注册成成功，账号信息推送
        sendRegisterMessage(lpMobile, fullName, password);

        return new MessageResponse(0, "添加企业管理完成！");
    }

    private void sendRegisterMessage(String lpMobile, String fullName, String password) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("name", fullName);
            params.put("password", password);
            messageService.registerMessage(true, lpMobile, params);
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
    private MessageResponse submitPayRecord(FopCompanyVo o, UserProp userProp) throws Exception {
        FopPayRecord payRecord = new FopPayRecord();
        payRecord.setRelationId(o.getId());
        payRecord.setRelationType(PayType.PAY_TYPE_MEMBER_JOIN_COMPANY);
        payRecord.setPayCategory(PayType.PAY_CATEGORY_MEMBER_JOIN);
        payRecord.setPayDate(DateUtil.getNowDate());

        return fopPayRecordService.submitPayRecord(payRecord, userProp);
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
    public MessageResponse updateFopCompany(FopCompanyVo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(1, "企业/机构全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(ResultCode.FAIL, "企业/机构法人姓名!");
        }
        if (CommonUtils.isBlank(o.getLpMobile())) {
            return new MessageResponse(ResultCode.FAIL, "企业/机构法人联系方式不能为空!");
        }

//        MessageResponse mm = validate(o);
//        if (ResultCode.FAIL == mm.getStatus()) {
//            return mm;
//        }
        int temp = this.fopCompanyDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(ResultCode.FAIL, "企业/机构名称重复！");
        }
        //修改法人信息
        MessageResponse pResp = updatePersonInfo(o, userProp);
        if (pResp.getStatus() == ResultCode.FAIL) {
            return pResp;
        }
        //公司组织信息
        insertOrUpdateCompanyOrgInfo(o, userProp);
        //对社会公益事业做过何种贡献信息
        insertOrUpdateContribution(o, userProp);

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopCompanyDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业/机构管理", "企业/机构管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更企业/机构管理完成！");
    }

    private MessageResponse updatePersonInfo(FopCompanyVo o, UserProp userProp) throws Exception {
        FopPerson person = fopPersonService.selectByMobile(o.getLpMobile());
        if (null != person) {
            person.setRealName(o.getRealName());
            person.setSex(o.getLpSex());
            person.setBirthDate(o.getLpBirthDt());
            person.setNativePlace(o.getLpNativePlace());
            person.setNationality(o.getLpNationality());
            person.setPolitical(o.getLpPolitical());
            person.setRecruitmentDate(o.getLpRecruitmentDate());
            person.setEducation(o.getLpEducation());
            person.setSkillJobTitle(o.getLpSkillJobTitle());
            person.setDeptPost(o.getLpDeptPost());
            person.setIdentityCard(o.getLpIdentityCard());
            person.setSocietyPost(o.getLpSocietyPost());
            person.setResume(o.getLpResume());
            person.setAchievement(o.getLpAchievement());

            return fopPersonService.updateFopPerson(person, userProp);
        }

        return new MessageResponse(ResultCode.SUCCESS, "变更法人信息完成！");
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
    public MessageResponse updateCompany(FopCompanyVo o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getFullName())) {
            return new MessageResponse(1, "企业/机构全称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPersonId())) {
            return new MessageResponse(1, "企业法人id不能为空！");
        }

        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }

        int temp = this.fopCompanyDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(ResultCode.FAIL, "企业/机构名称重复！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopCompanyDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更企业/机构管理", "企业/机构管理", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更企业/机构管理完成！");
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
        FopCompanyVo vo = this.fopCompanyDao.selectVoByPrimaryKey(id);
        //api 接口信息查询
        try {
            //vo = invokeApi(vo);
        } catch (Exception e) {
            logger.error("fop_company[" + id + "]接口查询异常：{}", e);
        }
        //附录查询
        setAnnexAttr(vo);
        rst.setValue(vo);
        return rst;
    }

    private void setAnnexAttr(FopCompanyVo vo) {
        List<FopCompanyAnnex> all = fopCompanyAnnexDao.findByComId(vo.getId());
        /**
         * 1-电子表格 2-同心助学 3-精准扶贫 4-企业党建
         */
        List<FopCompanyAnnex> es = new LinkedList<>();
        List<FopCompanyAnnex> cs = new LinkedList<>();
        List<FopCompanyAnnex> pa = new LinkedList<>();
        List<FopCompanyAnnex> epb = new LinkedList<>();
        for (FopCompanyAnnex annex : all) {
            switch (annex.getCategory()) {
                case "1"://电子表格
                    es.add(annex);
                    break;
                case "2"://同心助学
                    cs.add(annex);
                    break;
                case "3"://精准扶贫
                    pa.add(annex);
                    break;
                case "4"://企业党建
                    epb.add(annex);
                    break;
                default:
                    break;
            }
        }

        vo.setEsAnnex(es);
        vo.setCsAnnex(cs);
        vo.setPaAnnex(pa);
        vo.setEpbAnnex(epb);
    }

    private FopCompanyVo invokeApi(FopCompanyVo vo) {
        if (vo != null) {

            // 1、市工商局_企业变更记录信息 示例： 430721052016062700021
            if (StringUtil.isNotEmpty(vo.getLpIdentityCard())) {
                List<Map<String, Object>> list = DataSwapperApi.sgsj_qybgjlxx(vo.getLpIdentityCard());
                vo.setSgsj(CollectionUtils.isEmpty(list) ? null : list.get(0));
            }
            //2、市国税局_税务登记信息 -- 示例： 430702198704016527
            if (StringUtil.isNotEmpty(vo.getLpIdentityCard())) {
                List<Map<String, Object>> list = DataSwapperApi.sgsj_swdjxx(vo.getLpIdentityCard());
                vo.setGuosj(CollectionUtils.isEmpty(list) ? null : list.get(0));
            }
            //todo 3、市人社局_企业养老保险单位参保信息   示例：709696
            if (StringUtil.isNotEmpty(vo.getCreditCode())) {
                List<Map<String, Object>> list = DataSwapperApi.srsj_qyylbxdwsbxx(vo.getCreditCode());
                vo.setSrsj(CollectionUtils.isEmpty(list) ? null : list.get(0));
            }
        }

        return vo;
    }

    @Override
    public ResultResponse selectCompanyByPrimaryKey(String id) throws Exception {
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "企业详情", this.fopCompanyDao.selectVoByPrimaryKey(id));
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
    public MessageResponse deleteFopCompanyByFopCompanyId(String id, UserProp userProp) throws Exception {
        FopCompany company = fopCompanyDao.selectByPrimaryKey(id);
        if (null == company) {
            return new MessageResponse(ResultCode.FAIL, "无效企业编号");
        }
        //注销系统账户
        String account = sysAccountService.getAccount(FopConstant.COMPANY, id);
        Users users = usersService.selectByAccount(account);
        if (users != null) {
            MessageResponse rs = usersService.updateUsersStautsByPrimaryKey(users.getUserId(), "0", userProp);
            if (rs.getStatus() == ResultCode.FAIL) {
                return rs;
            }
        }
        //注销企业资料
        company.setStatus("0");
        company.setLastModifyDate(DateUtil.getNowDate());
        company.setLastModifyUserId(userProp.getUserId());
        company.setLastModifyUserName(userProp.getName());
        fopCompanyDao.updateByPrimaryKeySelective(company);

        this.dataBaseLogService.log("删除企业管理", "企业管理", String.valueOf(id),
                String.valueOf(id), "企业管理", userProp);
        return new MessageResponse(0, "企业管理删除完成");
    }

    @Override
    public Map<String, Object> selectCompany(Map<String, Object> params) throws Exception {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, String>> list = this.fopCompanyDao.selectPerson(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    @Override
    public FopCompany selectByDepartmentId(String departmentId) throws Exception {
        return fopCompanyDao.selectByDepartmentId(departmentId);
    }

    /**
     * 账号恢复
     *
     * @param id   唯一主键
     * @param type 会员类型 0-企业/个人/银行 1-团体
     */
    @Override
    public MessageResponse recoverData(String id, String type, UserProp userProp) throws Exception {
        if (type.equals(FopConstant.COMPANY)) {
            FopCompanyVo company = fopCompanyDao.selectVoByPrimaryKey(id);
            if (null == company) {
                return new MessageResponse(ResultCode.FAIL, "无效编号");
            }
            //恢复系统账号
            Users users = usersService.selectByAccount(company.getLpMobile());
            MessageResponse rs = usersService.updateUsersStautsByPrimaryKey(users.getUserId(), "1", userProp);
            if (rs.getStatus() == ResultCode.FAIL) {
                return rs;
            }
            //恢复会员身份
            company.setStatus("2");
            company.setLastModifyDate(DateUtil.getNowDate());
            company.setLastModifyUserId(userProp.getUserId());
            company.setLastModifyUserName(userProp.getName());
            fopCompanyDao.updateByPrimaryKeySelective(company);
            dataBaseLogService.log("会员恢复", "会员恢复", String.valueOf(id), String.valueOf(id), "企业管理", userProp);

            return new MessageResponse(ResultCode.SUCCESS, "数据恢复成功");
        } else if (type.equals(FopConstant.ASSOCIATION)) {
            FopAssociation association = fopAssociationDao.selectVoByPrimaryKey(id);
            if (null == association) {
                return new MessageResponse(ResultCode.FAIL, "无效编号");
            }
            //恢复系统账号
            Users users = usersService.selectByAccount(association.getPhoneNumber());
            MessageResponse rs = usersService.updateUsersStautsByPrimaryKey(users.getUserId(), "1", userProp);
            if (rs.getStatus() == ResultCode.FAIL) {
                return rs;
            }
            //恢复会员身份
            association.setStatus("2");
            association.setLastModifyDate(DateUtil.getNowDate());
            association.setLastModifyUserId(userProp.getUserId());
            association.setLastModifyUserName(userProp.getName());
            fopAssociationDao.updateByPrimaryKeySelective(association);
            dataBaseLogService.log("会员恢复", "会员恢复", String.valueOf(id), String.valueOf(id), "团体管理", userProp);

            return new MessageResponse(ResultCode.SUCCESS, "数据恢复成功");
        }

        return new MessageResponse(ResultCode.FAIL, "未知会员类型");
    }

    /**
     * 恢复会员身份
     *
     * @param id 唯一主键
     */
    @Override
    public MessageResponse reJoin(String id, UserProp userProp) throws Exception {
        FopCompanyVo company = fopCompanyDao.selectVoByPrimaryKey(id);
        if (null == company) {
            return new MessageResponse(ResultCode.FAIL, "无效编号");
        }
        if (!"1".equals(company.getStatus())) {
            return new MessageResponse(ResultCode.FAIL, "企业状态不合法");
        }
        //会员记录
        FopMember params = new FopMember();
        params.setRelationId(company.getId());
        params.setRelationType("0");// 0-企业会员 1-团体会员
        params.setMermberName(company.getFullName());
        MessageResponse ms = fopMemberService.memberJoinAudit(params, userProp);
        if (ResultCode.FAIL == ms.getStatus()) {
            return ms;
        }

        //恢复会员身份
        company.setStatus("2");
        company.setLastModifyDate(DateUtil.getNowDate());
        company.setLastModifyUserId(userProp.getUserId());
        company.setLastModifyUserName(userProp.getName());
        fopCompanyDao.updateByPrimaryKeySelective(company);
        dataBaseLogService.log("会员恢复", "退会再入会", String.valueOf(id), String.valueOf(id), "企业管理", userProp);

        return new MessageResponse(ResultCode.SUCCESS, "恢复成功");
    }

    /**
     * 上传企业档案附件
     *
     * @param url       附件地址
     * @param category  分类
     * @param companyId 公司ID
     * @return ResultResponse
     */
    @Override
    public ResultResponse uploadAnnex(String url, String category, String companyId) {
        FopCompanyAnnex annex = new FopCompanyAnnex();
        annex.setId(GUIDUtil.getGUID());
        annex.setCompanyId(companyId);
        annex.setCategory(category);
        annex.setFileUrl(url);
        annex.setFileType("image");
        annex.setFileName("");
        annex.setFileSize("");
        annex.setStatus("1");
        annex.setCreateDate(DateUtil.getNowDate());
        annex.setCreateUserId("system");
        annex.setCreateUserName("system");
        fopCompanyAnnexDao.insert(annex);

        return new ResultResponse(ResultCode.SUCCESS, "上传成功");
    }

    @Override
    public SingleResult<FopCompanyVo> findByPK(String id) {
        SingleResult<FopCompanyVo> rst = new SingleResult<>();
        FopCompanyVo vo = this.fopCompanyDao.selectVoByPrimaryKey(id);
        //附录查询
        setAnnexAttr(vo);
        rst.setValue(vo);
        return rst;
    }

    /**
     * 删除电子附件
     *
     * @param id 主键ID
     * @return MessageResponse
     */
    @Override
    public MessageResponse delAnnex(String id) {
        int i = fopCompanyAnnexDao.deleteByPrimaryKey(id);
        if (i != 1) {
            return new MessageResponse(ResultCode.FAIL, "删除失败");
        }
        return new MessageResponse(ResultCode.SUCCESS, "删除成功");
    }

    @Override
    public ResultResponse selectCompanyInfo(UserProp userProp) throws Exception {
        ResultResponse rr = getCompanyId(userProp);
        if (ResultCode.FAIL == rr.getStatus()) {
            return rr;
        }
        String id = (String) rr.getData();
        FopCompanyVo fc = fopCompanyDao.selectVoByPrimaryKey(id);
        FopPersonVo person = fopPersonService.selectFopPersonByPrimaryKey(fc.getPersonId()).getValue();
        List<FopCompanyOrgVo> olist = fopCompanyOrgService.findFopCompanyOrgListByCID(id);
        List<FopCompanyContributionVo> clist = fopCompanyContributionService.findFopCompanyContributionListByCID(id);
        fc.setPerson(person);
        fc.setOlist(olist);
        fc.setClist(clist);
        return new ResultResponse(ResultCode.SUCCESS, "企业详细信息", fc);

    }

    private ResultResponse getCompanyId(UserProp userProp) throws Exception {
        SingleResult<UsersVo> singleResult = usersService.selectUsersByPrimaryKey(userProp.getUserId());
        UsersVo user = singleResult.getValue();
        if (null == user) {
            return new ResultResponse(1, "没有注册");
        }
        if (CommonUtils.isBlank(user.getDepartmentId())) {
            return new ResultResponse(1, "账户没有绑定团体！");
        }
        FopCompany fc = fopCompanyDao.selectByDepartmentId(user.getDepartmentId());
        if (null == fc) {
            return new ResultResponse(ResultCode.FAIL, "fop团体不存在");
        }
        return new ResultResponse(ResultCode.SUCCESS, "id获取", fc.getId());
    }

    private MessageResponse validate(FopCompanyVo o) throws Exception {
        if (!CommonUtils.isBlank(o.getRegisteredCapital())) {
            if (!ValidateUtils.Two_point(String.valueOf(o.getRegisteredCapital()))) {
                return new MessageResponse(ResultCode.FAIL, "注册资金（万元）精确到小数点后两位");
            }
        }
        if (!CommonUtils.isBlank(o.getFixedAssets())) {
            if (!ValidateUtils.Two_point(String.valueOf(o.getFixedAssets()))) {
                return new MessageResponse(ResultCode.FAIL, "固有资产（万元）精确到小数点后两位");
            }
        }
        if (!CommonUtils.isBlank(o.getWorkingCapital())) {
            if (!ValidateUtils.Two_point(String.valueOf(o.getWorkingCapital()))) {
                return new MessageResponse(ResultCode.FAIL, "自有流动资金（万元）精确到小数点后两位");
            }
        }
        if (!CommonUtils.isBlank(o.getOwnSpace())) {
            if (!ValidateUtils.Two_point(String.valueOf(o.getOwnSpace()))) {
                return new MessageResponse(ResultCode.FAIL, "自有生产（经营）场地（㎡ ）精确到小数点后两位");
            }
        }
        if (!CommonUtils.isBlank(o.getTenancySpace())) {
            if (!ValidateUtils.Two_point(String.valueOf(o.getTenancySpace()))) {
                return new MessageResponse(ResultCode.FAIL, "租赁生产（经营）场地（㎡ ）精确到小数点后两位");
            }
        }
        if (!CommonUtils.isBlank(o.getPostcode())) {
            if (!ValidateUtils.Zipcode(String.valueOf(o.getPostcode()))) {
                return new MessageResponse(ResultCode.FAIL, "邮政编码数据格式不对");
            }
        }
        if (!CommonUtils.isBlank(o.getEmail())) {
            if (!ValidateUtils.Email(String.valueOf(o.getEmail()))) {
                return new MessageResponse(ResultCode.FAIL, "电子邮箱的格式不对");
            }
        }
        if (!CommonUtils.isBlank(o.getLaborContractNum())) {
            if (!ValidateUtils.Number(String.valueOf(o.getLaborContractNum()))) {
                return new MessageResponse(ResultCode.FAIL, "劳动合同签订人数为数字");
            }
        }

        if (!CommonUtils.isBlank(o.getAccTaxAmount())) {
            if (!ValidateUtils.Two_point(String.valueOf(o.getAccTaxAmount()))) {
                return new MessageResponse(ResultCode.FAIL, "累计纳税(万元) 精确到小数点后两位");
            }
        }
        if (!CommonUtils.isBlank(o.getYearTaxAmount())) {
            if (!ValidateUtils.Two_point(String.valueOf(o.getYearTaxAmount()))) {
                return new MessageResponse(ResultCode.FAIL, "当年纳税(万元) 精确到小数点后两位");
            }
        }
        if (!CommonUtils.isBlank(o.getPartyPhone())) {
            if (!ValidateUtils.Tel(o.getPartyPhone()) || !ValidateUtils.Mobile(o.getPartyPhone())) {
                return new MessageResponse(ResultCode.FAIL, "联系电话格式不对");
            }
        }
        if (!CommonUtils.isBlank(o.getUnionPhone())) {
            if (!ValidateUtils.Tel(o.getUnionPhone()) || !ValidateUtils.Mobile(o.getUnionPhone())) {
                return new MessageResponse(ResultCode.FAIL, "联系电话格式不对");
            }
        }

        return new MessageResponse(ResultCode.SUCCESS, "数据格式正确");
    }

}