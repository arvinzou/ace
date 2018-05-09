package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopFlowRecord;
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
    PageResult<FopFlowRecordVo> findFopFlowRecordList(FopFlowRecordQVo condition, int start, int limit, String orderBy) throws Exception;

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
    MessageResponse insertFopFlowRecord(FopFlowRecord obj, UserProp userProp) throws Exception;

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
    MessageResponse updateFopFlowRecord(FopFlowRecord obj, UserProp userProp) throws Exception;

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
    SingleResult<FopFlowRecordVo> selectFopFlowRecordByPrimaryKey(String id) throws Exception;

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
    MessageResponse deleteFopFlowRecordByFopFlowRecordId(String id, UserProp userProp) throws Exception;

    /**
     * 提交流程记录
     *
     * @param flowType 流程类型  ： FlowType.java
     * @param fromId   来源ID
     * @param userProp 操作人
     * @return 处理结果
     */
    MessageResponse submitFlowRecord(String flowId, String flowType, String fromId, UserProp userProp) throws Exception;

    /**
     * 功能描述: 流程审核
     *
     * @param: jsons 传入参数
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/7 11:45
     */
    MessageResponse audit(FopFlowRecord record, UserProp curUserProp) throws Exception;

    /**
     * 功能描述: 查询流程记录
     *
     * @param fromId   来源ID
     * @param flowType 流程类型
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 14:20
     */
    FopFlowRecord findByFromId(String fromId, String flowType);

    FopFlowRecord selectByPrimaryKey(String flowId);
}
