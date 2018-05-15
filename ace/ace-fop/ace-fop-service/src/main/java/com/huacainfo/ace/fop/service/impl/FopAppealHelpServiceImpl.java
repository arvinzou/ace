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
import com.huacainfo.ace.fop.common.constant.AuditResult;
import com.huacainfo.ace.fop.common.constant.FlowType;
import com.huacainfo.ace.fop.dao.FopAppealHelpDao;
import com.huacainfo.ace.fop.model.FopAppealHelp;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.service.FopAppealHelpService;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.vo.FopAppealHelpQVo;
import com.huacainfo.ace.fop.vo.FopAppealHelpVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fopAppealHelpService")
/**
 * @author: Arvin
 * @version: 2018-05-10
 * @Description: TODO(诉求服务)
 */
public class FopAppealHelpServiceImpl implements FopAppealHelpService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopAppealHelpDao fopAppealHelpDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诉求服务分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopAppealHelpVo>
     * @author: Arvin
     * @version: 2018-05-10
     */
    @Override
    public PageResult<FopAppealHelpVo> findFopAppealHelpList(FopAppealHelpQVo condition, int start,
                                                             int limit, String orderBy) throws Exception {
        PageResult<FopAppealHelpVo> rst = new PageResult<FopAppealHelpVo>();
        List<FopAppealHelpVo> list = this.fopAppealHelpDao.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopAppealHelpDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findAppealHelpList(FopAppealHelpQVo condition, int page, int limit, String orderBy) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.fopAppealHelpDao.findList(condition, (page - 1) * limit, limit, orderBy));
        map.put("total", this.fopAppealHelpDao.findCount(condition));
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "诉求服务列表", map);
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopAppealHelp
     * @Description: TODO(添加诉求服务)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-10
     */
    @Override
    public MessageResponse insertFopAppealHelp(FopAppealHelp o, UserProp userProp) throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        o.setRequestId(userProp.getUserId());
        o.setParentId("0");
        if (CommonUtils.isBlank(o.getRequestTitle())) {
            return new MessageResponse(1, "诉求标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getRequestDesc())) {
            return new MessageResponse(1, "诉求内容不能为空！");
        }
        o.setSubmitDate(new Date());
        if (CommonUtils.isBlank(o.getSubmitDate())) {
            return new MessageResponse(1, "提交时间不能为空！");
        }
        int temp = this.fopAppealHelpDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "诉求服务名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopAppealHelpDao.insertSelective(o);
        this.dataBaseLogService.log("添加诉求服务", "诉求服务", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加诉求服务完成！");
    }

    /**
     * @throws
     * @Title:updateFopAppealHelp
     * @Description: TODO(更新诉求服务)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-10
     */
    @Override
    public MessageResponse updateFopAppealHelp(FopAppealHelp o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        FopAppealHelp db = fopAppealHelpDao.selectByPrimaryKey(o.getId());
        if (CommonUtils.isBlank(db)) {
            new MessageResponse(0, "记录丢失");
        }
        o.setRequestId(db.getRequestId());
        o.setRequestType(db.getRequestType());
        o.setParentId(db.getParentId());

        if (CommonUtils.isBlank(o.getRequestId())) {
            return new MessageResponse(1, "发起人ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRequestType())) {
            return new MessageResponse(1, "发起人类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getParentId())) {
            return new MessageResponse(1, "父节点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getRequestTitle())) {
            return new MessageResponse(1, "诉求标题不能为空！");
        }
        if (CommonUtils.isBlank(o.getRequestDesc())) {
            return new MessageResponse(1, "诉求内容不能为空！");
        }
        if (CommonUtils.isBlank(o.getSubmitDate())) {
            return new MessageResponse(1, "提交时间不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        fopAppealHelpDao.updateByPrimaryKeySelective(o);
        dataBaseLogService.log("变更诉求服务", "诉求服务", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更诉求服务完成！");
    }

    /**
     * @throws
     * @Title:selectFopAppealHelpByPrimaryKey
     * @Description: TODO(获取诉求服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopAppealHelp>
     * @author: Arvin
     * @version: 2018-05-10
     */
    @Override
    public SingleResult<FopAppealHelpVo> selectFopAppealHelpByPrimaryKey(String id) throws Exception {
        SingleResult<FopAppealHelpVo> rst = new SingleResult<>();
        rst.setValue(this.fopAppealHelpDao.selectVoByPrimaryKey(id));
        return rst;
    }

    @Override
    public ResultResponse selectAppealHelpByPrimaryKey(String id) throws Exception {
        ResultResponse rst = new ResultResponse(ResultCode.SUCCESS, "诉求详情", this.fopAppealHelpDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopAppealHelpByFopAppealHelpId
     * @Description: TODO(删除诉求服务)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-10
     */
    @Override
    public MessageResponse deleteFopAppealHelpByFopAppealHelpId(String id, UserProp userProp) throws Exception {
        FopAppealHelp o = fopAppealHelpDao.selectByPrimaryKey(id);
        if (CommonUtils.isBlank(o)) {
            new MessageResponse(0, "记录丢失");
        }

        o.setStatus("-1");
        o.setLastModifyDate(DateUtil.getNowDate());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        fopAppealHelpDao.updateByPrimaryKeySelective(o);

        this.dataBaseLogService.log("删除诉求服务", "诉求服务", id, id, "诉求服务", userProp);
        return new MessageResponse(0, "诉求服务删除完成！");
    }

    /**
     * 功能描述: 确认回复
     *
     * @param id
     * @param userProp
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 18:33
     */
    @Override
    public MessageResponse audit(String id, UserProp userProp) throws Exception {
        FopAppealHelp obj = fopAppealHelpDao.selectByPrimaryKey(id);
        if (CommonUtils.isBlank(obj)) {
            new MessageResponse(0, "记录丢失");
        }

        if (obj.getStatus().equals("2")) {
            return new MessageResponse(ResultCode.FAIL, "请勿重复发布");
        }
        //提交审核流程
        String flowId = GUIDUtil.getGUID();
        MessageResponse rs = fopFlowRecordService.submitFlowRecord(flowId, FlowType.REQUEST_HELP, id, userProp);
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

    /**
     * 功能描述: 根据主键查询记录
     *
     * @param id
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/10 18:51
     */
    @Override
    public FopAppealHelp selectByPrimaryKey(String id) {

        return fopAppealHelpDao.selectByPrimaryKey(id);
    }

}
