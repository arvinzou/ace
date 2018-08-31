package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.*;
import com.huacainfo.ace.fop.dao.*;
import com.huacainfo.ace.fop.model.*;
import com.huacainfo.ace.fop.service.*;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import com.huacainfo.ace.fop.vo.FopFlowRecordQVo;
import com.huacainfo.ace.fop.vo.FopFlowRecordVo;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.MessageTemplateService;
import com.huacainfo.ace.portal.service.UserinfoService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UserinfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fopFlowRecordService")
/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(流程记录)
 */
public class FopFlowRecordServiceImpl implements FopFlowRecordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FopFlowRecordDao fopFlowRecordDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private FopMemberService fopMemberService;
    @Autowired
    private FopCompanyDao fopCompanyDao;
    @Autowired
    private FopCompanyService fopCompanyService;
    @Autowired
    private FopCompanyService companyService;
    @Autowired
    private FopAssociationDao fopAssociationDao;
    @Autowired
    private FopAssociationService fopAssociationService;
    @Autowired
    private FopPayRecordService fopPayRecordService;
    @Autowired
    private FopGeHelpService fopGeHelpService;
    @Autowired
    private FopActivityService fopActivityService;
    @Autowired
    private FopProjectService fopProjectService;
    @Autowired
    private FopAppealHelpService fopAppealHelpService;
    @Autowired
    private FopFinanceProjectDao fopFinanceProjectDao;
    @Autowired
    private FopFinanceProjectService fopFinanceProjectService;

    @Autowired
    private FopLoanProductDao fopLoanProductDao;
    @Autowired
    private FopLoanProductService fopLoanProductService;

    @Autowired
    private InformationServiceDao informationServiceDao;
    @Autowired
    private InformationServiceService informationServiceService;

    @Autowired
    private FopQuestionDao fopQuestionDao;
    @Autowired
    private FopQuestionService fopQuestionService;
    @Autowired
    private SysAccountService sysAccountService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MessageTemplateService messageTemplateService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(流程记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopFlowRecordVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public PageResult<FopFlowRecordVo> findFopFlowRecordList(FopFlowRecordQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<FopFlowRecordVo> rst = new PageResult<FopFlowRecordVo>();
        List<FopFlowRecordVo> list = this.fopFlowRecordDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopFlowRecordDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopFlowRecord
     * @Description: TODO(添加流程记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse insertFopFlowRecord(FopFlowRecord o, UserProp userProp)
            throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getFromId())) {
            return new MessageResponse(1, "记录来源ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getFlowType())) {
            return new MessageResponse(1, "流程类型不能为空！");
        }
//        if (CommonUtils.isBlank(o.getPersonId())) {
//            return new MessageResponse(1, "审核人不能为空！");
//        }
//        if (CommonUtils.isBlank(o.getAuditResult())) {
//            return new MessageResponse(1, "审核结果不能为空！");
//        }
//        if (CommonUtils.isBlank(o.getAuditDate())) {
//            return new MessageResponse(1, "审核时间不能为空！");
//        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        o.setLastModifyDate(DateUtil.getNowDate());
        this.fopFlowRecordDao.insertSelective(o);
        this.dataBaseLogService.log("添加流程记录", "流程记录", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加流程记录完成！");
    }

    /**
     * @throws
     * @Title:updateFopFlowRecord
     * @Description: TODO(更新流程记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse updateFopFlowRecord(FopFlowRecord o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getFromId())) {
            return new MessageResponse(1, "记录来源ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getFlowType())) {
            return new MessageResponse(1, "流程类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getPersonId())) {
            return new MessageResponse(1, "审核人不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditResult())) {
            return new MessageResponse(1, "审核结果不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditDate())) {
            return new MessageResponse(1, "审核时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopFlowRecordDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更流程记录", "流程记录", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更流程记录完成！");
    }

    /**
     * @throws
     * @Title:selectFopFlowRecordByPrimaryKey
     * @Description: TODO(获取流程记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopFlowRecord>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public SingleResult<FopFlowRecordVo> selectFopFlowRecordByPrimaryKey(String id) throws Exception {
        SingleResult<FopFlowRecordVo> rst = new SingleResult<FopFlowRecordVo>();
        rst.setValue(this.fopFlowRecordDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopFlowRecordByFopFlowRecordId
     * @Description: TODO(删除流程记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @Override
    public MessageResponse deleteFopFlowRecordByFopFlowRecordId(String id,
                                                                UserProp userProp) throws Exception {
        this.fopFlowRecordDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除流程记录", "流程记录", String.valueOf(id),
                String.valueOf(id), "流程记录", userProp);
        return new MessageResponse(0, "流程记录删除完成！");
    }

    /**
     * 提交流程记录
     *
     * @param flowType 流程类型  ： FlowType.java
     * @param fromId   来源ID
     * @param userProp 操作人
     * @return 处理结果
     */
    @Override
    public MessageResponse submitFlowRecord(String flowId, String flowType, String fromId,
                                            UserProp userProp) throws Exception {

        //插入审核流程
        FopFlowRecord flowRecord = new FopFlowRecord();
        flowRecord.setId(flowId);
        flowRecord.setFromId(fromId);
        flowRecord.setFlowType(flowType);
//        flowRecord.setPersonId(userProp.getUserId());
//        flowRecord.setAuditResult(auditResult);
//        flowRecord.setAuditOpinion("系统自动审核");
//        flowRecord.setAuditDate(DateUtil.getNowDate());
//        flowRecord.setStatus("1");//1-未审核，2-已审核

        return insertFopFlowRecord(flowRecord, userProp);
    }

    /**
     * 功能描述: 流程审核
     *
     * @param record
     * @param userProp
     * @param: jsons 传入参数
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/7 11:45
     */
    @Override
    public MessageResponse audit(FopFlowRecord record, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(record.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(record.getFlowType())) {
            return new MessageResponse(1, "流程类型不能为空！");
        }
        if (CommonUtils.isBlank(record.getAuditResult())) {
            return new MessageResponse(1, "审核结果不能为空！");
        }

        //补全资料
        FopFlowRecord db = fopFlowRecordDao.selectByPrimaryKey(record.getId());
        if (null == db) {
            return new MessageResponse(ResultCode.FAIL, "流程丢失");
        }
        record.setFromId(db.getFromId());
        MessageResponse rs;
        switch (record.getFlowType()) {
            case FlowType.MEMBER_JOIN_COMPANY:
                rs = memberJoinAudit(record, userProp);//  2018/5/7 企业会员申请审核
                break;
            case FlowType.MEMBER_JOIN_ASSOCIATION:
                rs = memberJoinAudit(record, userProp);//  2018/5/7 团体会员申请审核
                break;
            case FlowType.MEMBER_PAY:
                rs = memberPay(record, userProp);//  2018/5/7 会员缴费审核
                break;
            case FlowType.MEMBER_QUIT_COMPANY:
                rs = memberQuitAudit(record, userProp);// 企业会员退会审核
                break;
            case FlowType.MEMBER_QUIT_ASSOCIATION:
                rs = null;// TODO: 2018/5/7 团体会员退会审核
                break;
            case FlowType.GE_HELP:
                rs = geHelpRelease(record, userProp);// 政企服务审核
                break;
            case FlowType.PARTY_WORK:
                rs = partWork(record, userProp);// 党建工作发布审核
                break;
            case FlowType.COOP_PROJECT:
                rs = coopProject(record, userProp);// 合作交流项目审核
                break;
            case FlowType.REQUEST_HELP:
                rs = requestHelp(record, userProp);// 诉求服务回复确认
                break;
            case FlowType.FINANCE_PROJECT:
                rs = financeProject(record, userProp);// 银企服务
                break;
            case FlowType.LOAN_PROJECT:
                rs = loanProject(record, userProp);// 诉求服务回复确认
                break;
            case FlowType.INFORMATION_SERVICE:
                rs = informationService(record, userProp);// 诉求服务回复确认
                break;
            case FlowType.LAW_HELP:
                rs = lawHelp(record, userProp);// 法律帮助审核
                break;
            default:
                rs = null;
                break;
        }
        if (null == rs) {
            return new MessageResponse(ResultCode.FAIL, "未知流程类型");
        } else if (ResultCode.FAIL == rs.getStatus()) {
            return rs;
        }

        record.setAuditDate(DateUtil.getNowDate());
        record.setPersonId(userProp.getUserId());
        record.setStatus("2");
        record.setLastModifyDate(DateUtil.getNowDate());
        record.setLastModifyUserName(userProp.getName());
        record.setLastModifyUserId(userProp.getUserId());
        fopFlowRecordDao.updateByPrimaryKeySelective(record);
        this.dataBaseLogService.log("变更流程记录", "流程记录", "",
                record.getId(), record.getId(), userProp);
        return new MessageResponse(0, "流程审核成功");
    }

    /**
     * 企业会员退会审核
     */
    private MessageResponse memberQuitAudit(FopFlowRecord record, UserProp userProp) throws Exception {
        FopCompanyVo fopCompany = fopCompanyDao.selectVoByPrimaryKey(record.getFromId());
        if (null == fopCompany) {
            return new MessageResponse(ResultCode.FAIL, "企业资料丢失");
        }
        //审核通过 - 退出会员，变更会员身份
        if (AuditResult.PASS.equals(record.getAuditResult())) {
            int delCount = fopMemberService.deleteByRelationId(fopCompany.getId());
            if (delCount > 0) {
                fopCompany.setStatus("1");//0-已删除 1 - 非会员 2 - 会员
//                fopCompany.setLastModifyDate(new Date());
//                fopCompany.setLastModifyUserName(userProp.getName());
//                fopCompany.setLastModifyUserId(userProp.getUserId());
                MessageResponse ms = fopCompanyService.updateFopCompany(fopCompany, userProp);
                if (ms.getStatus() == ResultCode.SUCCESS) {
                    return new MessageResponse(ResultCode.SUCCESS, "审核成功");
                }
            }

            return new MessageResponse(ResultCode.FAIL, "审核失败");
        }
        //推送微信结果通知
//        sendNoticeToUser(fopCompany.getFullName(), FopConstant.COMPANY, fopCompany.getId(), record);

        return new MessageResponse(ResultCode.SUCCESS, "审核成功");

    }

    /**
     * 法律帮助审核
     *
     * @param record
     * @param userProp
     * @return
     */
    private MessageResponse lawHelp(FopFlowRecord record, UserProp userProp) throws Exception {
        FopQuestion obj = fopQuestionDao.selectByPrimaryKey(record.getFromId());
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }
        //状态变更
        String status = AuditResult.PASS.equals(record.getAuditResult()) ? "2" : "3";
        obj.setStatus(status);

        return fopQuestionService.updateFopQuestion(obj, userProp);
    }

    /**
     * 信息服务 -- 审核逻辑
     *
     * @param record
     * @param userProp
     * @return
     * @throws Exception
     */
    private MessageResponse informationService(FopFlowRecord record, UserProp userProp) throws Exception {
        InformationService obj = informationServiceDao.selectByPrimaryKey(record.getFromId());
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }
        //状态变更
        String status = AuditResult.PASS.equals(record.getAuditResult()) ? "2" : "3";
        obj.setStatus(status);

        return informationServiceService.updateInformationService(obj, userProp);
    }

    /**
     * 金融产品 -- 审核逻辑
     *
     * @param record
     * @param userProp
     * @return
     * @throws Exception
     */
    private MessageResponse loanProject(FopFlowRecord record, UserProp userProp) throws Exception {
        FopLoanProduct obj = fopLoanProductDao.selectByPrimaryKey(record.getFromId());
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }
        //状态变更
        String status = AuditResult.PASS.equals(record.getAuditResult()) ? "2" : "3";
        obj.setStatus(status);

        return fopLoanProductService.updateFopLoanProduct(obj, userProp);
    }

    /**
     * 银企服务审核逻辑
     *
     * @param record
     * @param userProp
     * @return
     */
    private MessageResponse financeProject(FopFlowRecord record, UserProp userProp) throws Exception {
        FopFinanceProject obj = fopFinanceProjectDao.selectByPrimaryKey(record.getFromId());
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }
        //状态变更
        String status = AuditResult.PASS.equals(record.getAuditResult()) ? "2" : "3";
        obj.setStatus(status);

        return fopFinanceProjectService.updateFopFinanceProject(obj, userProp);
    }

    /**
     * 功能描述:  诉求服务回复确认
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 18:52
     */
    private MessageResponse requestHelp(FopFlowRecord record, UserProp userProp) throws Exception {

//        if (AuditResult.PASS.equals(record.getAuditResult())) {
        FopAppealHelp obj = fopAppealHelpService.selectByPrimaryKey(record.getFromId());
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }

        //状态变更
        String status = AuditResult.PASS.equals(record.getAuditResult()) ? "2" : "3";
        obj.setStatus(status);

        return fopAppealHelpService.updateFopAppealHelp(obj, userProp);
//        }
//
//        return new MessageResponse(ResultCode.SUCCESS, "审核成功");
    }

    /**
     * 功能描述: 合作交流项目审核
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 17:10
     */
    private MessageResponse coopProject(FopFlowRecord record, UserProp userProp) throws Exception {
        FopProject obj = fopProjectService.selectByPrimaryKey(record.getFromId());
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }

        //状态变更
        String status = AuditResult.PASS.equals(record.getAuditResult()) ? "2" : "3";
        obj.setStatus(status);


        return fopProjectService.updateFopProject(obj, userProp);
    }

    /**
     * 功能描述: 党建工作发布审核
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/9 15:49
     */
    private MessageResponse partWork(FopFlowRecord record, UserProp userProp) throws Exception {
//        if (AuditResult.PASS.equals(record.getAuditResult())) {
        FopActivity obj = fopActivityService.selectByPrimaryKey(record.getFromId());
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }

        //状态变更
        String status = AuditResult.PASS.equals(record.getAuditResult()) ? "2" : "3";
        obj.setStatus(status);

        return fopActivityService.updateFopActivity(obj, userProp);
//        }
//
//
//        return new MessageResponse(ResultCode.SUCCESS, "审核成功");
    }

    /**
     * 功能描述:  政企服务发布审核
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/9 11:23
     */
    private MessageResponse geHelpRelease(FopFlowRecord record, UserProp userProp) throws Exception {
//        if (AuditResult.PASS.equals(record.getAuditResult())) {
        FopGeHelp obj = fopGeHelpService.selectByPrimaryKey(record.getFromId());
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录丢失");
        }
        //状态变更
        String status = AuditResult.PASS.equals(record.getAuditResult()) ? "2" : "3";
        obj.setStatus(status);

        return fopGeHelpService.updateFopGeHelp(obj, userProp);
//        }
//        return new MessageResponse(ResultCode.SUCCESS, "审核成功");

    }

    /**
     * 会员缴费审核
     *
     * @param record   流程记录
     * @param userProp 操作员
     * @return
     */
    private MessageResponse memberPay(FopFlowRecord record, UserProp userProp) throws Exception {
        if (AuditResult.PASS.equals(record.getAuditResult())) {
            FopPayRecord payRecord = fopPayRecordService.selectByPrimaryKey(record.getFromId());
            if (null == payRecord) {
                return new MessageResponse(ResultCode.FAIL, "缴费记录丢失");
            }

            payRecord.setPayResult("1"); //0-未缴纳，1-已缴纳
            payRecord.setLastModifyDate(DateUtil.getNowDate());
            payRecord.setLastModifyUserId(userProp.getUserId());
            payRecord.setLastModifyUserName(userProp.getName());
            return fopPayRecordService.updateFopPayRecord(payRecord, userProp);
        }
        return new MessageResponse(ResultCode.SUCCESS, "审核成功");
    }

    /**
     * 功能描述: 查询流程记录
     *
     * @param fromId   来源ID
     * @param flowType 流程类型
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 14:20
     */
    @Override
    public FopFlowRecord findByFromId(String fromId, String flowType) {

        return fopFlowRecordDao.findByFromId(fromId, flowType);
    }

    @Override
    public FopFlowRecord selectByPrimaryKey(String flowId) {
        return fopFlowRecordDao.selectByPrimaryKey(flowId);
    }

    @Override
    public MessageResponse test(String id) throws Exception {
        UserProp userProp = new UserProp();
        userProp.setName("system-test");
        userProp.setUserId("8888");

        FopFlowRecord record = fopFlowRecordDao.selectByPrimaryKey(id);
        if (null == record) {
            return new MessageResponse(ResultCode.FAIL, "***************NPE******************");
        }
        return memberJoinAudit(record, userProp);
    }

    /**
     * 提交退会申请
     *
     * @param companyId 企业ID
     * @return ResultResponse
     */
    @Override
    public ResultResponse applyQuit(String companyId) throws Exception {
        FopCompanyVo vo = fopCompanyDao.selectVoByPrimaryKey(companyId);
        if (null == vo) {
            return new ResultResponse(ResultCode.FAIL, "企业资料异常");
        }
        if ("2".equals(vo.getStatus())) {
            return new ResultResponse(ResultCode.FAIL, "会员状态异常");
        }
//        int auditRecord = fopFlowRecordDao.isExit();

        UserProp userProp = new UserProp();
        userProp.setUserId(vo.getId());
        userProp.setName(vo.getFullName());
        userProp.setActiveSyId("fop");

        MessageResponse ms = submitFlowRecord(GUIDUtil.getGUID(), FlowType.MEMBER_QUIT_COMPANY, vo.getId(), userProp);
        if (ms.getStatus() == ResultCode.SUCCESS) {
            return new ResultResponse(ResultCode.SUCCESS, "申请成功");
        }
        return new ResultResponse(ms);
    }

    /**
     * 会员申请审核
     *
     * @param record
     * @param userProp
     * @return
     */
    private MessageResponse memberJoinAudit(FopFlowRecord record, UserProp userProp) throws Exception {
        if (FlowType.MEMBER_JOIN_COMPANY.equals(record.getFlowType())) {
            FopCompany fopCompany = fopCompanyDao.selectByPrimaryKey(record.getFromId());
            if (null == fopCompany) {
                return new MessageResponse(ResultCode.FAIL, "企业资料丢失");
            }
            //审核通过 - 加入会员
            if (AuditResult.PASS.equals(record.getAuditResult())) {
                FopMember fopMember = new FopMember();
                fopMember.setRelationId(record.getFromId());
                fopMember.setRelationType("0");// 0-企业会员 1-团体会员
                fopMember.setMermberName(fopCompany.getFullName());
                MessageResponse rs = fopMemberService.memberJoinAudit(fopMember, userProp);
                if (rs.getStatus() == ResultCode.SUCCESS) {
                    fopCompany.setStatus("2");//0-已删除 1 - 非会员 2 - 会员
                    fopCompany.setLastModifyDate(new Date());
                    fopCompany.setLastModifyUserName(userProp.getName());
                    fopCompany.setLastModifyUserId(userProp.getUserId());
                    fopCompanyDao.updateByPrimaryKeySelective(fopCompany);
                }
            }
            //推送微信结果通知
            sendNoticeToUser(fopCompany.getFullName(), FopConstant.COMPANY, fopCompany.getId(), record);

            return new MessageResponse(ResultCode.SUCCESS, "审核成功");
        } else if (FlowType.MEMBER_JOIN_ASSOCIATION.equals(record.getFlowType())) {
            FopAssociation association = fopAssociationDao.selectByPrimaryKey(record.getFromId());
            if (null == association) {
                return new MessageResponse(ResultCode.FAIL, "商协会资料丢失");
            }
            if (AuditResult.PASS.equals(record.getAuditResult())) {
                FopMember fopMember = new FopMember();
                fopMember.setRelationId(record.getFromId());
                fopMember.setRelationType("1");// 0-企业会员 1-团体会员
                fopMember.setMermberName(association.getFullName());
                MessageResponse rs = fopMemberService.memberJoinAudit(fopMember, userProp);
                if (rs.getStatus() == ResultCode.SUCCESS) {
                    association.setLastModifyDate(new Date());
                    association.setLastModifyUserName(userProp.getName());
                    association.setLastModifyUserId(userProp.getUserId());
                    association.setStatus("2");//0-已删除 1 - 非会员 2 - 会员
                    fopAssociationDao.updateByPrimaryKeySelective(association);
                }
            }
            //推送微信结果通知
            sendNoticeToUser(association.getFullName(), FopConstant.ASSOCIATION, association.getId(), record);

            return new MessageResponse(ResultCode.SUCCESS, "审核成功");
        }

        return new MessageResponse(ResultCode.FAIL, "未知会员类型");
    }

    /**
     * 推送微信结果通知
     *
     * @param nickName
     * @param relationType
     * @param relationId
     * @param record
     */
    private void sendNoticeToUser(String nickName, String relationType, String relationId, FopFlowRecord record) {
        try {
            String account = sysAccountService.getAccount(relationType, relationId);
            Users users = usersService.selectByAccount(account);
            //此处逻辑绑定为unionid
            UserinfoVo uVo = userinfoService.selectUserinfoByPrimaryKey(users.getOpenId()).getValue();
            String openid = uVo.getOpenid();

            String tmplCode = MsgTmplCode.BIS_CONFIRM_NOTICE;
            Map<String, Object> params = new HashMap<>();
            //kafka所需内容
            params.put("service", "messageTemplateService");
            params.put("sysId", "fop");
            params.put("tmplCode", tmplCode);
            //发送消息内容
            params.put("openid", openid);
//            params.put("url", "www.baidu.com");
//        params.put("first", "哈哈哈哈哈");
            //data
            params.put("name", nickName);
            params.put("title", "会员资质审核");
            String rs = AuditResult.PASS.equals(record.getAuditResult()) ? "已通过" : "被驳回";
            params.put("content", "会员资质审核\n" +
                    "审核结果：" + rs + "\n" +
                    "审核意见：" + (CommonUtils.isEmpty(record.getAuditOpinion()) ? "" : record.getAuditOpinion()));
            //kafka消息推送
            kafkaProducerService.sendMsg(KafkaConstant.TOPIC_NAME, params);
//            ResultResponse r = messageTemplateService.send("fop", tmplCode, params);
//            logger.debug("********************************\n" +  JsonUtil.toJson(r));

        } catch (Exception e) {
            logger.error("会员资质审核 -[{}]- 审核 - 消息推送失败：{}", relationId, e);
        }
    }


}
