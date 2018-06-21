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
import com.huacainfo.ace.fop.common.constant.FopConstant;
import com.huacainfo.ace.fop.common.constant.ModulesType;
import com.huacainfo.ace.fop.dao.*;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.model.InformationService;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopQuestionService;
import com.huacainfo.ace.fop.service.InformationServiceService;
import com.huacainfo.ace.fop.vo.*;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UsersVo;
import javassist.bytecode.analysis.MultiType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("informationServiceService")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(信息服务)
 */
public class InformationServiceServiceImpl implements InformationServiceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InformationServiceDao informationServiceDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private UsersService usersService;
    @Autowired
    private FopCompanyDao fopCompanyDao;

    @Autowired
    private FopAssociationDao fopAssociationDao;

    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    @Autowired
    private FopProjectDao fopProjectDao;
    @Autowired
    private FopGeHelpDao fopGeHelpDao;
    @Autowired
    private FopQuestionDao fopQuestionDao;

    @Autowired
    private FopQuestionService fopQuestionService;

    @Autowired
    private FopQuestionnaireResultDao fopQuestionnaireResultDao;

    @Autowired
    private FopFinanceProjectDao fopFinanceProjectDao;

    @Autowired
    private FopLoanProductDao fopLoanProductDao;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(信息服务分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <InformationServiceVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public PageResult<InformationServiceVo> findInformationServiceList(InformationServiceQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<InformationServiceVo> rst = new PageResult<InformationServiceVo>();
        List<InformationServiceVo> list = this.informationServiceDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.informationServiceDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @param condition
     * @param page
     * @param limit
     * @param orderBy
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse InformationServiceList(InformationServiceQVo condition, int page, int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.informationServiceDao.findList(condition, (page - 1) * limit, limit, orderBy));
        map.put("total", this.informationServiceDao.findCount(condition));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "信息服务列表", map);
        return rst;
    }

    /**
     * @throws
     * @Title:insertInformationService
     * @Description: TODO(添加信息服务)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse insertInformationService(InformationService o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getModules())) {
            return new MessageResponse(1, "模块不能为空！");
        }

        SingleResult<UsersVo> singleResult = usersService.selectUsersByPrimaryKey(userProp.getUserId());
        UsersVo user = singleResult.getValue();
        if (null == user) {
            return new MessageResponse(1, "没有注册");
        }
        if (CommonUtils.isBlank(user.getDepartmentId())) {
            return new MessageResponse(1, "账户没有绑定企业！");
        }
        FopAssociation fa = fopAssociationDao.selectByDepartmentId(user.getDepartmentId());
        FopCompany fc = fopCompanyDao.selectByDepartmentId(user.getDepartmentId());
        if (null == fc) {
            if (null == fa) {
                return new MessageResponse(1, "账户没有绑定企业！");
            }
            o.setRelationId(fa.getId());
            o.setRelationType(FopConstant.ASSOCIATION);
        } else {
            if ("3".equals(fc.getCompanyType())) {
                return new MessageResponse(ResultCode.FAIL, "注册银行不能发布");
            }
            o.setRelationId(fc.getId());
            o.setRelationType(FopConstant.COMPANY);
        }

        o.setReleaseDate(new Date());
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.informationServiceDao.insertSelective(o);
        this.dataBaseLogService.log("添加信息服务", "信息服务", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加信息服务完成！");
    }

    /**
     * @throws
     * @Title:updateInformationService
     * @Description: TODO(更新信息服务)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse updateInformationService(InformationService o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }

        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.informationServiceDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更信息服务", "信息服务", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更信息服务完成！");
    }

    /**
     * @throws
     * @Title:selectInformationServiceByPrimaryKey
     * @Description: TODO(获取信息服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<InformationService>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public SingleResult<InformationServiceVo> selectInformationServiceByPrimaryKey(String id) throws Exception {
        SingleResult<InformationServiceVo> rst = new SingleResult<InformationServiceVo>();
        InformationServiceVo ffp = this.informationServiceDao.selectVoByPrimaryKey(id);
        if (ModulesType.TALENT_INFO.equals(ffp.getModules())) {
            ffp.setComments(fopQuestionService.findCommentList(ffp.getId()));
        }

        return rst;
    }

    @Override
    public ResultResponse InformationServiceByPrimaryKey(String id) throws Exception {
        InformationServiceVo ffp = this.informationServiceDao.selectVoByPrimaryKey(id);
        if (ModulesType.TALENT_INFO.equals(ffp.getModules())) {
            ffp.setComments(fopQuestionService.findCommentList(ffp.getId()));
        }
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "信息服务详情", ffp);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteInformationServiceByInformationServiceId
     * @Description: TODO(删除信息服务)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @Override
    public MessageResponse deleteInformationServiceByInformationServiceId(String id, UserProp
            userProp) throws Exception {
        InformationService informationService = informationServiceDao.selectByPrimaryKey(id);
        if (null == informationService) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失！");
        }
        informationService.setStatus("0");
        informationService.setLastModifyUserId(userProp.getUserId());
        informationService.setLastModifyUserName(userProp.getName());
        informationService.setLastModifyDate(DateUtil.getNowDate());
        informationServiceDao.updateByPrimaryKeySelective(informationService);
        return new MessageResponse(0, "信息服务删除完成！");
    }

    /**
     * 功能描述:  审核
     *
     * @param id
     * @param auditResult  审核结果 -- 0-通过，1-不通过
     * @param auditOpinion 审核备注
     * @param: id Fop_Finance_Project.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    @Override
    public MessageResponse audit(String id, String auditResult, String auditOpinion, UserProp userProp) throws Exception {
        InformationService obj = informationServiceDao.selectByPrimaryKey(id);
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失");
        }
        if (obj.getStatus().equals("2")) {
            return new MessageResponse(ResultCode.FAIL, "请勿重复发布");
        }
        //提交审核流程
        String flowId = GUIDUtil.getGUID();
        MessageResponse rs = fopFlowRecordService.submitFlowRecord(flowId, FlowType.INFORMATION_SERVICE, id, userProp);
        if (ResultCode.FAIL == rs.getStatus()) {
            return rs;
        }
        //审核
        FopFlowRecord record = fopFlowRecordService.selectByPrimaryKey(flowId);
        record.setAuditResult(auditResult);
        record.setAuditOpinion(auditOpinion);
        MessageResponse rs1 = fopFlowRecordService.audit(record, userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            throw new CustomException(rs1.getErrorMessage());
        }

        return new MessageResponse(ResultCode.SUCCESS, "审核成功");
    }

    @Override
    public ResultResponse publishStatistics(UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(userProp)) {
            return new ResultResponse(1, "没有登陆");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        SingleResult<UsersVo> singleResult = usersService.selectUsersByPrimaryKey(userProp.getUserId());
        UsersVo user = singleResult.getValue();
        if (null == user) {
            return new ResultResponse(1, "没有注册");
        }
        if (CommonUtils.isBlank(user.getDepartmentId())) {
            return new ResultResponse(1, "账户没有绑定企业1！");
        }
        FopAssociation fa = fopAssociationDao.selectByDepartmentId(user.getDepartmentId());
        String relationId;
        FopCompany fc = fopCompanyDao.selectByDepartmentId(user.getDepartmentId());
        if (fa != null) {
            relationId = fa.getId();
        } else if (fc != null) {
            relationId = fc.getId();
        } else {
            return new ResultResponse(1, "账户没有绑定企业2");
        }
        ;
        String status = "2";
        InformationServiceQVo condition = new InformationServiceQVo();
        condition.setRelationId(relationId);
        condition.setStatus(status);
        condition.setModules(ModulesType.CORPORATE_STYLE);
        map.put("CORPORATE_STYLE", this.informationServiceDao.findCount(condition));

        condition.setModules(ModulesType.ENTERPRISE_PRODUCTS);
        map.put("ENTERPRISE_PRODUCTS", this.informationServiceDao.findCount(condition));

        condition.setModules(ModulesType.TALENT_INFO);
        map.put("TALENT_INFO", this.informationServiceDao.findCount(condition));

        condition.setModules(ModulesType.INVESTMENT_INFO);
        map.put("INVESTMENT_INFO", this.informationServiceDao.findCount(condition));

        condition.setModules(ModulesType.POLICY_DOCUMENTS);
        map.put("POLICY_DOCUMENTS", this.informationServiceDao.findCount(condition));

        condition.setModules(ModulesType.BRAND_PROMOTION);
        map.put("BRAND_PROMOTION", this.informationServiceDao.findCount(condition));
        /*项目统计*/
        FopProjectQVo fopProjectQVo = new FopProjectQVo();
        fopProjectQVo.setStatus("2");
        fopProjectQVo.setRelationId(relationId);
        map.put("PROJECT", this.fopProjectDao.findCount(fopProjectQVo));
        /*政企服务*/
        FopGeHelpQVo fopGeHelpQVo = new FopGeHelpQVo();
        fopGeHelpQVo.setStatus("2");
        fopGeHelpQVo.setRequestId(relationId);
        map.put("GEHELP", this.fopGeHelpDao.findCount(fopGeHelpQVo));
        /*法律帮助*/
        FopQuestionQVo fopQuestionQVo = new FopQuestionQVo();
        fopQuestionQVo.setStatus("2");
        fopQuestionQVo.setSourceType("0");
        fopQuestionQVo.setRelationId(relationId);
        map.put("LOWHELP", this.fopQuestionDao.findCount(fopQuestionQVo));

        /*满意度调查*/
        FopQuestionnaireResultQVo fopQuestionnaireResultQVo = new FopQuestionnaireResultQVo();
        fopQuestionnaireResultQVo.setStatus("2");
        fopQuestionnaireResultQVo.setRelationId(relationId);
        map.put("QUESTION_RESULT", this.fopQuestionnaireResultDao.findCount(fopQuestionnaireResultQVo));

         /*金融*/
        FopFinanceProjectQVo fopFinanceProject = new FopFinanceProjectQVo();
        fopFinanceProject.setStatus("2");
        fopFinanceProject.setCompanyId(relationId);
        map.put("financial", this.fopFinanceProjectDao.findCount(fopFinanceProject));

          /*金融*/
        FopLoanProductQVo fopLoanProductQVo = new FopLoanProductQVo();
        fopLoanProductQVo.setStatus("2");
        fopLoanProductQVo.setCompanyId(relationId);
        map.put("loan_product", this.fopLoanProductDao.findCount(fopLoanProductQVo));
        return new ResultResponse(ResultCode.SUCCESS, "获取数据统计", map);
    }
}
