package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.common.constant.AuditResult;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.dao.FopGeHelpDao;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.model.FopGeHelp;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopGeHelpService;
import com.huacainfo.ace.fop.vo.FopGeHelpQVo;
import com.huacainfo.ace.fop.vo.FopGeHelpVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;

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
        //o.setId(String.valueOf(new Date().getTime()));
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

        o.setCreateDate(new Date());
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


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopGeHelpDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更政企服务", "政企服务", "",
                o.getId(), o.getId(), userProp);
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
        fopGeHelp.setStatus("-1");
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
    public MessageResponse audit(String id, UserProp userProp) throws Exception {
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
        record.setAuditResult(AuditResult.PASS);
        record.setAuditOpinion("系统自动审核");
        record.setRemark("系统自动审核");
        MessageResponse rs1 = fopFlowRecordService.audit(record, userProp);
        if (ResultCode.FAIL == rs1.getStatus()) {
            throw new CustomException(rs1.getErrorMessage());
        }

        return new MessageResponse(ResultCode.SUCCESS, "发布成功");
    }

    @Override
    public FopGeHelp selectByPrimaryKey(String id) {
        return fopGeHelpDao.selectByPrimaryKey(id);
    }
}
