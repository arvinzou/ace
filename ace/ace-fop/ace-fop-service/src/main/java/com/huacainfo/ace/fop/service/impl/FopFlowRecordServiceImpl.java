package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopFlowRecordDao;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.vo.FopFlowRecordQVo;
import com.huacainfo.ace.fop.vo.FopFlowRecordVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        List<FopFlowRecordVo> list = this.fopFlowRecordDao.findList(condition,
                start, start + limit, orderBy);
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
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopFlowRecordDao.insertSelective(o);
        this.dataBaseLogService.log("添加流程记录", "流程记录", "", o.getId(),
                o.getId(), userProp);
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
     * 企业会员注册，自动审核通过，成为会员
     *
     * @param flowType    流程类型  ： FlowType.java
     * @param fromId      来源ID
     * @param auditResult 审核结果 0 - 通过，1 -不通过
     * @param userProp    操作人
     * @return 处理结果
     */
    @Override
    public MessageResponse memberJoinAutoAudit(String flowType, String fromId, String auditResult,
                                               UserProp userProp) throws Exception {

        //插入审核流程
        FopFlowRecord flowRecord = new FopFlowRecord();
        flowRecord.setId(GUIDUtil.getGUID());
        flowRecord.setFromId(fromId);
        flowRecord.setFlowType(flowType);
        flowRecord.setPersonId(userProp.getUserId());
        flowRecord.setAuditResult(auditResult);
        flowRecord.setAuditOpinion("系统自动审核");
        flowRecord.setAuditDate(DateUtil.getNowDate());

        return insertFopFlowRecord(flowRecord, userProp);
    }


}
