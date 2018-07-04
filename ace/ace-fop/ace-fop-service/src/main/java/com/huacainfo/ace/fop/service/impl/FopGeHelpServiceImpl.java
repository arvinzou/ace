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
import com.huacainfo.ace.fop.dao.FopAssociationDao;
import com.huacainfo.ace.fop.dao.FopCompanyDao;
import com.huacainfo.ace.fop.dao.FopGeHelpDao;
import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.model.FopGeHelp;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopGeHelpService;
import com.huacainfo.ace.fop.service.MessageService;
import com.huacainfo.ace.fop.vo.FopGeHelpQVo;
import com.huacainfo.ace.fop.vo.FopGeHelpVo;
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

@Service("fopGeHelpService")
/**
 * @author: Arvin
 * @version: 2018-05-08
 * @Description: TODO(政企服务)
 */
public class FopGeHelpServiceImpl implements FopGeHelpService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopGeHelpDao fopGeHelpDao;

    @Autowired
    private FopCompanyDao fopCompanyDao;

    @Autowired
    private FopAssociationDao fopAssociationDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private MessageService messageService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(政企服务分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopGeHelpVo>
     * @author: Arvin
     * @version: 2018-05-08
     */
    @Override
    public PageResult<FopGeHelpVo> findFopGeHelpList(FopGeHelpQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<FopGeHelpVo> rst = new PageResult<FopGeHelpVo>();
        List<FopGeHelpVo> list = this.fopGeHelpDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopGeHelpDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public PageResult<FopGeHelpVo> findFopGeHelpListVo(FopGeHelpQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<FopGeHelpVo> rst = new PageResult<FopGeHelpVo>();
        List<FopGeHelpVo> lists = this.fopGeHelpDao.findList(condition, start, start + limit, orderBy);
        for (FopGeHelpVo list : lists) {
            List<FopGeHelp> itmps = this.fopGeHelpDao.findProcessList(list.getId());
            String ProcessDetail = "";
            for (FopGeHelp itmp : itmps) {
                ProcessDetail += "      " + itmp.getProcessDetail() + "——" + DateUtil.toStr(itmp.getReleaseDate(), "yyyy-MM-dd HH:mm:ss") + "\n\n";
            }
            list.setProcessDetail(ProcessDetail);
        }
        rst.setRows(lists);
        if (start <= 1) {
            int allRows = this.fopGeHelpDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findGeHelpList(FopGeHelpQVo condition, int page, int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.fopGeHelpDao.findList(condition, (page - 1) * limit, limit, orderBy));
        map.put("total", this.fopGeHelpDao.findCount(condition));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "政企服务列表", map);
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopGeHelp
     * @Description: TODO(添加政企服务)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-08
     */
    @Override
    public MessageResponse insertFopGeHelp(FopGeHelp o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getRequestId())) {
            return new MessageResponse(1, "发起人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRequestType())) {
            return new MessageResponse(1, "发起人类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getParentId())) {
            return new MessageResponse(1, "父节点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setReleaseDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopGeHelpDao.insertSelective(o);
        /*this.dataBaseLogService.log("添加政企服务", "政企服务", "",
                o.getId(), o.getId(), userProp);*/
        return new MessageResponse(0, "添加政企服务完成！");
    }


    @Override
    public MessageResponse insertProcess(FopGeHelp o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProcessDetail())) {
            return new MessageResponse(1, "添加的进度不能为空！");
        }
        if (CommonUtils.isBlank(o.getParentId())) {
            return new MessageResponse(1, "父节点ID不能为空！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setReleaseDate(new Date());
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopGeHelpDao.insertSelective(o);
        this.dataBaseLogService.log("添加进度信息", "进度信息", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加进度信息完成！");
    }

    /**
     * @throws
     * @Title:insertFopGeHelp
     * @Description: TODO(添加政企服务)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-08
     */
    @Override
    public MessageResponse insertGeHelp(FopGeHelp o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }

        /*获取登陆用户信息*/
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
            o.setRequestId(fa.getId());
            o.setRequestType(FopConstant.ASSOCIATION);
        } else {
            if ("3".equals(fc.getCompanyType())) {
                return new MessageResponse(ResultCode.FAIL, "注册银行不能发布");
            }
            o.setRequestId(fc.getId());
            o.setRequestType(FopConstant.COMPANY);
        }

        if (CommonUtils.isBlank(o.getRequestType())) {
            return new MessageResponse(1, "发起人类型不能为空！");
        }
        o.setParentId("0");
        if (CommonUtils.isBlank(o.getParentId())) {
            return new MessageResponse(1, "父节点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getContent())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        o.setCreateDate(new Date());
        o.setReleaseDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopGeHelpDao.insertSelective(o);
        this.dataBaseLogService.log("添加政企服务", "政企服务", "",
                o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加政企服务完成！");
    }

    /**
     * @throws
     * @Title:updateFopGeHelp
     * @Description: TODO(更新政企服务)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-08
     */
    @Override
    public MessageResponse updateFopGeHelp(FopGeHelp o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        FopGeHelp fopGeHelp = fopGeHelpDao.selectByPrimaryKey(o.getId());
        if (null == fopGeHelp) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失！");
        }
        o.setRequestId(fopGeHelp.getRequestId());
        o.setRequestType(fopGeHelp.getRequestType());
        o.setParentId(fopGeHelp.getParentId());
        if (CommonUtils.isBlank(o.getRequestId())) {
            return new MessageResponse(1, "发起人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRequestType())) {
            return new MessageResponse(1, "发起人类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getParentId())) {
            return new MessageResponse(1, "父节点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "内容不能为空！");
        }
//        o.setStatus("1");
        o.setLastModifyDate(new Date());
        o.setReleaseDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopGeHelpDao.updateByPrimaryKeySelective(o);
        /*this.dataBaseLogService.log("变更政企服务", "政企服务", "",
                o.getId(), o.getId(), userProp);*/
        return new MessageResponse(0, "变更政企服务完成！");
    }

    @Override
    public MessageResponse updateGeHelp(FopGeHelp o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        FopGeHelp fopGeHelp = fopGeHelpDao.selectByPrimaryKey(o.getId());
        if (null == fopGeHelp) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getTitle())) {
            return new MessageResponse(1, "内容不能为空！");
        }
        o.setLastModifyDate(new Date());
        o.setReleaseDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopGeHelpDao.updateByPrimaryKeySelective(o);
        /*this.dataBaseLogService.log("变更政企服务", "政企服务", "",
                o.getId(), o.getId(), userProp);*/
        return new MessageResponse(0, "变更政企服务完成！");
    }

    /**
     * @throws
     * @Title:selectFopGeHelpByPrimaryKey
     * @Description: TODO(获取政企服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopGeHelp>
     * @author: Arvin
     * @version: 2018-05-08
     */
    @Override
    public SingleResult<FopGeHelpVo> selectFopGeHelpByPrimaryKey(String id) throws Exception {
        SingleResult<FopGeHelpVo> rst = new SingleResult<FopGeHelpVo>();
        rst.setValue(this.fopGeHelpDao.selectVoByPrimaryKey(id));
        return rst;
    }

    @Override
    public SingleResult<FopGeHelpVo> selectFopGeHelpByPrimaryKeyVo(String id) throws Exception {
        SingleResult<FopGeHelpVo> rst = new SingleResult<FopGeHelpVo>();
        List<FopGeHelp> lists = this.fopGeHelpDao.findProcessList(id);
        String ProcessDetail = "";
        for (FopGeHelp list : lists) {
            ProcessDetail += "\\t" + list.getProcessDetail() + "\\n" + "\\t\\t\\t" + list.getReleaseDate().toString() + "\\n";
        }
        FopGeHelpVo result = this.fopGeHelpDao.selectVoByPrimaryKey(id);
        result.setProcessDetail(ProcessDetail);
        rst.setValue(result);
        return rst;
    }


    @Override
    public ResultResponse selectGeHelpByPrimaryKey(String id) throws Exception {
        List<FopGeHelp> lists = this.fopGeHelpDao.findProcessList(id);
        FopGeHelpVo list = this.fopGeHelpDao.selectVoByPrimaryKey(id);
        list.setProcess(lists);
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "政企诉求详情", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopGeHelpByFopGeHelpId
     * @Description: TODO(删除政企服务)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-08
     */
    @Override
    public MessageResponse deleteFopGeHelpByFopGeHelpId(String id, UserProp userProp) throws Exception {
        FopGeHelp fopGeHelp = fopGeHelpDao.selectByPrimaryKey(id);
        if (null == fopGeHelp) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失！");
        }
        fopGeHelp.setStatus("0");
        fopGeHelp.setLastModifyUserId(userProp.getUserId());
        fopGeHelp.setLastModifyUserName(userProp.getName());
        fopGeHelp.setLastModifyDate(DateUtil.getNowDate());
        fopGeHelpDao.updateByPrimaryKeySelective(fopGeHelp);
        dataBaseLogService.log("删除政企服务", "政企服务",
                String.valueOf(id),
                String.valueOf(id), "政企服务", userProp);
        return new MessageResponse(0, "政企服务删除完成！");
    }

    /**
     * 功能描述: 审核发布
     *
     * @param:id fop_ge_help.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:18
     */
    @Override
    public MessageResponse audit(String id, String auditResult, String auditOpinion, UserProp userProp) throws Exception {

        FopGeHelp fopGeHelp = fopGeHelpDao.selectByPrimaryKey(id);
        if (null == fopGeHelp) {
            return new MessageResponse(ResultCode.FAIL, "记录数据丢失");
        }
        if (fopGeHelp.getStatus().equals("2")) {
            return new MessageResponse(ResultCode.FAIL, "请勿重复发布");
        }
        //提交审核流程
        String flowId = GUIDUtil.getGUID();
        MessageResponse rs = fopFlowRecordService.submitFlowRecord(flowId, FlowType.GE_HELP, id, userProp);
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
        //消息提醒
        sendMessageNotice(fopGeHelp, auditResult, auditOpinion);

        return new MessageResponse(ResultCode.SUCCESS, "发布成功");
    }

    private void sendMessageNotice(FopGeHelp fopGeHelp, String auditResult, String auditOpinion) {
        try {
            ResultResponse response = messageService.geHelpAuditMessage(fopGeHelp, auditResult, auditOpinion);
            logger.debug("政企服务审核消息发送结果[" + fopGeHelp.getId() + "]:" + response.getInfo());
        } catch (Exception e) {
            logger.error("政企服务审核消息发送异常[" + fopGeHelp.getId() + "]：{}", e);
        }
    }

    @Override
    public FopGeHelp selectByPrimaryKey(String id) {
        return fopGeHelpDao.selectByPrimaryKey(id);
    }
}