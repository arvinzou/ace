package com.huacainfo.ace.fop.service.impl;


import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
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
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.common.constant.PayType;
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.model.FopPayRecord;
import com.huacainfo.ace.fop.model.FopPerson;
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
        condition.setCompanyType("w");
        if (condition.getIsCompany()) {
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
            f.setRealName(item.getRealName());
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
        String password = RandomValidateCode.CreateRadom(8, 2);
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
        //入库企业信息
        o.setPersonId(person.getId());
        o.setCompanyType(CommonUtils.isBlank(o.getCompanyType()) ? "0" : o.getCompanyType());
        o.setDepartmentId(department.getDepartmentId());
        o.setCreateDate(new Date());
        String companyType = o.getCompanyType();
        String status = "3".equals(companyType) ? "2" : "1";
        o.setStatus(status);
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopCompanyDao.insertSelective(o);
        this.dataBaseLogService.log("添加企业管理", "企业管理", "", o.getId(),
                o.getId(), userProp);
        //自动提交会员申请流程
        if (!"3".equals(companyType)) {//银行添加无需提交审核
            MessageResponse rs3 = fopFlowRecordService.submitFlowRecord(GUIDUtil.getGUID(),
                    FlowType.MEMBER_JOIN_COMPANY, o.getId(), userProp);
            if (ResultCode.FAIL == rs3.getStatus()) {
                return rs3;
            }
        }
        //自动提交缴费记录
//        MessageResponse rs4 = submitPayRecord(o, userProp);
//        if (ResultCode.FAIL == rs4.getStatus()) {
//            return rs4;
//        }
        //注册成功消息推送
        sendRegisterMessage(o.getLpMobile(), o.getFullName(), password);

        return new MessageResponse(0, "添加企业管理完成！");
    }

    @Override
    public MessageResponse insertCompany(String fullName, String lpMobile) throws Exception {
        if (!CommonUtils.isValidMobile(lpMobile)) {
            return new MessageResponse(ResultCode.FAIL, "不是手机号码");
        }
        FopCompanyVo o = new FopCompanyVo();
        o.setFullName(fullName);
        o.setLpMobile(lpMobile);
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
        o.setCompanyType(CommonUtils.isBlank(o.getCompanyType()) ? "0" : o.getCompanyType());
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

        MessageResponse mm = validate(o);
        if (ResultCode.FAIL == mm.getStatus()) {
            return mm;
        }
        int temp = this.fopCompanyDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(ResultCode.FAIL, "企业/机构名称重复！");
        }
        //修改法人信息
        updatePersonInfo(o, userProp);

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

            fopPersonService.updateFopPerson(person, userProp);
        }

        return new MessageResponse(0, "变更企业/机构管理完成！");
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
        rst.setValue(this.fopCompanyDao.selectVoByPrimaryKey(id));
        return rst;
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
        MessageResponse rs = usersService.updateUsersStautsByPrimaryKey(users.getUserId(), "0", userProp);
        if (rs.getStatus() == ResultCode.FAIL) {
            return rs;
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
        return new MessageResponse(ResultCode.SUCCESS, "数据格式正确");
    }

}