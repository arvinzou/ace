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
import com.huacainfo.ace.fop.dao.*;
import com.huacainfo.ace.fop.model.*;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopQuestionService;
import com.huacainfo.ace.fop.service.MessageService;
import com.huacainfo.ace.fop.service.SysAccountService;
import com.huacainfo.ace.fop.vo.FopQuestionQVo;
import com.huacainfo.ace.fop.vo.FopQuestionVo;
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

@Service("fopQuestionService")
/**
 * @author: Arvin
 * @version: 2018-05-07
 * @Description: TODO(法律帮助/政府诉求)
 */
public class FopQuestionServiceImpl implements FopQuestionService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopQuestionDao fopQuestionDao;
    @Autowired
    private SysAccountService sysAccountService;

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
    private FopFinanceProjectDao fopFinanceProjectDao;

    @Autowired
    private FopLoanProductDao fopLoanProductDao;

    @Autowired
    private InformationServiceDao informationServiceDao;

    @Autowired
    private FopProjectDao fopProjectDao;
    @Autowired
    private MessageService messageService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(法律帮助/政府诉求分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopQuestionVo>
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public PageResult<FopQuestionVo> findFopQuestionList(FopQuestionQVo condition,
                                                         int start, int limit, String orderBy) throws Exception {
        PageResult<FopQuestionVo> rst = new PageResult<FopQuestionVo>();
        List<FopQuestionVo> list;
        if (CommonUtils.isBlank(condition.getSourceType())) {
            list = this.fopQuestionDao.findListVo(condition, start, limit, orderBy);
        } else {
            list = this.fopQuestionDao.findList(condition, start, limit, orderBy);
        }

        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopQuestionDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findQuestionList(FopQuestionQVo condition, int page, int limit, String orderBy) throws Exception {
        condition.setSourceType("0");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.fopQuestionDao.findList(condition, (page - 1) * limit, limit, orderBy));
        map.put("total", this.fopQuestionDao.findCount(condition));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "法律帮助列表", map);
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopQuestion
     * @Description: TODO(添加法律帮助/政府诉求)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public MessageResponse insertFopQuestion(FopQuestion o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        int temp = this.fopQuestionDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "法律帮助/政府诉求名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopQuestionDao.insertSelective(o);
        this.dataBaseLogService.log("添加法律帮助/政府诉求", "法律帮助/政府诉求", "",
                o.getTitle(), o.getTitle(), userProp);
        return new MessageResponse(0, "添加法律帮助/政府诉求完成！");
    }

    @Override
    public MessageResponse insertLawQuestion(FopQuestion o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        o.setSourceType("0");
        o.setReleaseDate(new Date());
        o.setParentId("0");
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubType())) {
            return new MessageResponse(1, "类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubType())) {
            return new MessageResponse(1, "内容不能为空！");
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
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopQuestionDao.insertSelective(o);
        this.dataBaseLogService.log("添加法律帮助/政府诉求", "法律帮助/政府诉求", "",
                o.getTitle(), o.getTitle(), userProp);
        return new MessageResponse(0, "添加法律帮助/政府诉求完成！");
    }

    @Override
    public MessageResponse insertQuestion(FopQuestion o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getReply())) {
            return new MessageResponse(1, "留言不能为空");
        }
        if (CommonUtils.isBlank(o.getParentId())) {
            return new MessageResponse(1, "评论对象ID不能为空");
        }
        if (CommonUtils.isBlank(o.getSourceType())) {
            return new MessageResponse(1, "评论类型不能为空");
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
            o.setRelationId(fc.getId());
            o.setRelationType(FopConstant.COMPANY);
        }
        if ("2".equals(o.getSourceType())) {
            FopFinanceProject fopFinanceProject = fopFinanceProjectDao.selectByPrimaryKey(o.getParentId());
            o.setTitle(fopFinanceProject.getFinanceTitle());
            o.setContent(fopFinanceProject.getFinanceContent());
        } else if ("3".equals(o.getSourceType())) {
            FopLoanProduct fopLoanProduct = fopLoanProductDao.selectByPrimaryKey(o.getParentId());
            o.setTitle(fopLoanProduct.getProductName());
            o.setContent(fopLoanProduct.getDescription());
        } else if ("4".equals(o.getSourceType())) {
            FopProject fopProject = fopProjectDao.selectByPrimaryKey(o.getParentId());
            o.setTitle(fopProject.getProjectName());
            o.setContent(fopProject.getCoopDesc());
        } else if ("5".equals(o.getSourceType())) {
            InformationService informationService = informationServiceDao.selectByPrimaryKey(o.getParentId());
            o.setTitle(informationService.getTitle());
            o.setContent(informationService.getContent());
        }
        o.setCreateDate(new Date());
        o.setReleaseDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopQuestionDao.insertSelective(o);
        this.dataBaseLogService.log("添加法律帮助/政府诉求", "法律帮助/政府诉求", "",
                o.getTitle(), o.getTitle(), userProp);
        return new MessageResponse(0, "添加法律帮助/政府诉求完成！");
    }

    /**
     * @throws
     * @Title:updateFopQuestion
     * @Description: TODO(更新法律帮助/政府诉求)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public MessageResponse updateFopQuestion(FopQuestion o, UserProp userProp)
            throws Exception {

        o.setStatus("1");
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopQuestionDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更法律帮助/政府诉求", "法律帮助/政府诉求", "",
                o.getTitle(),
                o.getTitle(), userProp);
        return new MessageResponse(0, "变更法律帮助/政府诉求完成！");
    }

    /**
     * @throws
     * @Title:selectFopQuestionByPrimaryKey
     * @Description: TODO(获取法律帮助/政府诉求)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopQuestion>
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public SingleResult<FopQuestionVo> selectFopQuestionByPrimaryKey(String id) throws Exception {
        SingleResult
                <FopQuestionVo> rst = new SingleResult
                <FopQuestionVo>();
        rst.setValue(this.fopQuestionDao.selectByPrimaryKey(id));
        return rst;
    }

    @Override
    public ResultResponse selectQuestionByPrimaryKey(String id) throws Exception {
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "Question详情", this.fopQuestionDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopQuestionByFopQuestionId
     * @Description: TODO(删除法律帮助/政府诉求)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    @Override
    public MessageResponse deleteFopQuestionByFopQuestionId(String id,
                                                            UserProp userProp) throws Exception {
        FopQuestion fopQuestion = fopQuestionDao.selectByPrimaryKey(id);
        if (null == fopQuestion) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失！");
        }
        fopQuestion.setStatus("0");
        fopQuestion.setLastModifyUserId(userProp.getUserId());
        fopQuestion.setLastModifyUserName(userProp.getName());
        fopQuestion.setLastModifyDate(DateUtil.getNowDate());
        fopQuestionDao.updateByPrimaryKeySelective(fopQuestion);
        dataBaseLogService.log("删除法律帮助/政府诉求", "法律帮助/政府诉求",
                String.valueOf(id),
                String.valueOf(id), "法律帮助/政府诉求", userProp);
        return new MessageResponse(0, "法律帮助/政府诉求删除完成！");
    }

    @Override
    public List<FopQuestionVo> findCommentList(String parentId) throws Exception {
        List<FopQuestionVo> list = this.fopQuestionDao.findCommentList(parentId);
        if (list.size() == 0) {
            return null;
        }
        for (FopQuestionVo fp : list) {
            String parent = fp.getId();
            List<FopQuestionVo> list1 = findCommentList(parent);
            fp.setChildren(list1);
        }
        return list;
    }

    @Override
    public MessageResponse audit(String id, String auditResult, String auditOpinion, UserProp userProp) throws Exception {
        FopQuestion obj = fopQuestionDao.selectByPrimaryKey(id);
        if (null == obj) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失");
        }
        if (obj.getStatus().equals("2")) {
            return new MessageResponse(ResultCode.FAIL, "请勿重复发布");
        }
        //提交审核流程
        String flowId = GUIDUtil.getGUID();
        MessageResponse rs = fopFlowRecordService.submitFlowRecord(flowId, FlowType.LAW_HELP, id, userProp);
        if (ResultCode.FAIL == rs.getStatus()) {
            return rs;
        }
        //自动审核通过
        FopFlowRecord record = fopFlowRecordService.selectByPrimaryKey(flowId);
        record.setAuditResult(auditResult);
        record.setAuditOpinion(auditOpinion);
        MessageResponse rs1 = fopFlowRecordService.audit(record, userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            throw new CustomException(rs1.getErrorMessage());
        }
        //审核结果推送给客户
        //消息提醒
        sendMessageNotice(obj, auditResult, auditOpinion);

        return new MessageResponse(ResultCode.SUCCESS, "发布成功");
    }

    private void sendMessageNotice(FopQuestion obj, String auditResult, String auditOpinion) {
        try {
            messageService.lawQuestionAuditMessage(obj, auditResult, auditOpinion);
        } catch (Exception e) {
            logger.error("法律帮助 -[{}]- 审核 - 消息推送失败", obj.getId());
        }
    }
}
