package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import com.huacainfo.ace.fop.vo.FopFlowRecordQVo;
import com.huacainfo.ace.fop.vo.FopFlowRecordVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(流程记录)
 */
public interface FopFlowRecordService {
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
    public abstract PageResult<FopFlowRecordVo> findFopFlowRecordList(FopFlowRecordQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopFlowRecord
     * @Description: TODO(添加流程记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopFlowRecord(FopFlowRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopFlowRecord
     * @Description: TODO(更新流程记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopFlowRecord(FopFlowRecord obj, UserProp userProp) throws Exception;

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
    public abstract SingleResult<FopFlowRecordVo> selectFopFlowRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopFlowRecordByFopFlowRecordId
     * @Description: TODO(删除流程记录)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopFlowRecordByFopFlowRecordId(String id, UserProp userProp) throws Exception;

    /**
     * 企业会员注册，自动审核通过，成为会员
     *
     * @param flowType  流程类型  ： FlowType.java
     * @param companyVo 企业会员资料
     * @param userProp  操作人
     * @return 处理结果
     */
    MessageResponse memberJoinAutoAudit(String flowType, FopCompanyVo companyVo, UserProp userProp) throws Exception;
}
