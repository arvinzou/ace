package com.huacainfo.ace.fundtown.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fundtown.model.ProcessNode;
import com.huacainfo.ace.fundtown.model.ProcessNodeRes;
import com.huacainfo.ace.fundtown.vo.ProcessNodeQVo;
import com.huacainfo.ace.fundtown.vo.ProcessNodeVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2018-07-03
 * @Description: TODO(入驻流程节点)
 */
public interface ProcessNodeService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻流程节点分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ProcessNodeVo>
     * @author: Arvin
     * @version: 2018-07-03
     */
    PageResult<ProcessNodeVo> findProcessNodeList(ProcessNodeQVo condition,
                                                  int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertProcessNode
     * @Description: TODO(添加入驻流程节点)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse insertProcessNode(ProcessNode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateProcessNode
     * @Description: TODO(更新入驻流程节点)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse updateProcessNode(ProcessNode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectProcessNodeByPrimaryKey
     * @Description: TODO(获取入驻流程节点)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ProcessNode>
     * @author: Arvin
     * @version: 2018-07-03
     */
    SingleResult<ProcessNodeVo> selectProcessNodeByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteProcessNodeByProcessNodeId
     * @Description: TODO(删除入驻流程节点)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-03
     */
    MessageResponse deleteProcessNodeByProcessNodeId(String id, UserProp userProp) throws Exception;

    /**
     * 获取入驻流程节点
     *
     * @return
     */
    List<ProcessNode> findNodeList();

    /**
     * 获取节点文件资源
     *
     * @param nodeId 节点ID  fundtown.process_node.id
     * @return
     */
    Map<String, List<ProcessNodeRes>> getNodeResList(String nodeId);
}
