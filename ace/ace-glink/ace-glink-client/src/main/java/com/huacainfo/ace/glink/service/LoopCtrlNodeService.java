package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.LoopCtrlNode;
import com.huacainfo.ace.glink.vo.LoopCtrlNodeVo;
import com.huacainfo.ace.glink.vo.LoopCtrlNodeQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: heshuang
 * @version: 2019-04-15
 * @Description: TODO(区级整体控制)
 */
public interface LoopCtrlNodeService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(区级整体控制分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <LoopCtrlNodeVo>
     * @author: heshuang
     * @version: 2019-04-15
     */
    PageResult
            <LoopCtrlNodeVo> findLoopCtrlNodeList(LoopCtrlNodeQVo condition,
                                                  int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertLoopCtrlNode
     * @Description: TODO(添加区级整体控制)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    MessageResponse insertLoopCtrlNode(LoopCtrlNode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateLoopCtrlNode
     * @Description: TODO(更新区级整体控制)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    MessageResponse updateLoopCtrlNode(LoopCtrlNode obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectLoopCtrlNodeByPrimaryKey
     * @Description: TODO(获取区级整体控制)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LoopCtrlNode>
     * @author: heshuang
     * @version: 2019-04-15
     */
    SingleResult
            <LoopCtrlNodeVo> selectLoopCtrlNodeByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteLoopCtrlNodeByLoopCtrlNodeId
     * @Description: TODO(删除区级整体控制)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    MessageResponse deleteLoopCtrlNodeByLoopCtrlNodeId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核区级整体控制)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    MessageResponse importXls(List
                                      <Map
                                              <String
                                                      , Object>> list, UserProp userProp) throws Exception;


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
     * @author: heshuang
     * @version: 2019-04-15
     */
    ListResult
            <Map
                    <String
                            , Object>> getList(Map
                                                       <String
                                                               , Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: heshuang
     * @version: 2019-04-15
     */
    Map
            <String
                    , Object> getListByCondition(Map
                                                         <String
                                                                 , Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除区级整体控制 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    MessageResponse deleteLoopCtrlNodeByLoopCtrlNodeIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: heshuang
     * @version: 2019-04-15
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 修改状态
     *
     * @param id
     * @param state
     * @return
     * @throws Exception
     */
    MessageResponse updateState(String id, String state) throws Exception;
}
