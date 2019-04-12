package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.TopNode;
import com.huacainfo.ace.glink.vo.TopNodeVo;
import com.huacainfo.ace.glink.vo.TopNodeQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: huacai003
 * @version: 2019-04-09
 * @Description: TODO(节点管理)
 */
public interface TopNodeService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节点管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TopNodeVo>
     * @author: huacai003
     * @version: 2019-04-09
     */
    PageResult<TopNodeVo> findTopNodeList(TopNodeQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTopNode
     * @Description: TODO(添加节点管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    MessageResponse insertTopNode(TopNode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTopNode
     * @Description: TODO(更新节点管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    MessageResponse updateTopNode(TopNode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTopNodeByPrimaryKey
     * @Description: TODO(获取节点管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TopNode>
     * @author: huacai003
     * @version: 2019-04-09
     */
    SingleResult
            <TopNodeVo> selectTopNodeByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTopNodeByTopNodeId
     * @Description: TODO(删除节点管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    MessageResponse deleteTopNodeByTopNodeId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核节点管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;



    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult
     * <Map
     * <String
     * ,Object>>
     * @author: huacai003
     * @version: 2019-04-09
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: huacai003
     * @version: 2019-04-09
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除节点管理）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
     MessageResponse deleteTopNodeByTopNodeIds(String[] id,UserProp userProp) throws Exception;
     MessageResponse updateStation(String[] id,String stationCode, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-09
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;


    MessageResponse insertImportXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;
}