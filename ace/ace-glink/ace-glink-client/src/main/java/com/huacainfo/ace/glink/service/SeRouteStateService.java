package com.huacainfo.ace.glink.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.SeRouteState;
import com.huacainfo.ace.glink.vo.SeRouteStateVo;
import com.huacainfo.ace.glink.vo.SeRouteStateQVo;
import java.util.Map;
import java.util.List;
/**
* @author: huacai003
* @version: 2019-04-18
* @Description:  TODO(路由器运行状态)
*/
public interface SeRouteStateService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(路由器运行状态分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<SeRouteStateVo>
    * @throws
    * @author: huacai003
    * @version: 2019-04-18
    */
    PageResult
    <SeRouteStateVo> findSeRouteStateList(SeRouteStateQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertSeRouteState
        * @Description: TODO(添加路由器运行状态)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: huacai003
        * @version: 2019-04-18
        */
        MessageResponse insertSeRouteState(SeRouteState obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateSeRouteState
        * @Description: TODO(更新路由器运行状态)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: huacai003
        * @version: 2019-04-18
        */
        MessageResponse updateSeRouteState(SeRouteState obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectSeRouteStateByPrimaryKey
        * @Description: TODO(获取路由器运行状态)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<SeRouteState>
        * @throws
        * @author: huacai003
        * @version: 2019-04-18
        */
        SingleResult
        <SeRouteStateVo> selectSeRouteStateByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteSeRouteStateBySeRouteStateId
            * @Description: TODO(删除路由器运行状态)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2019-04-18
            */
            MessageResponse deleteSeRouteStateBySeRouteStateId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核路由器运行状态)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2019-04-18
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;


            /**
            *
            * @Title:importXls
            * @Description: TODO(Excel导入资源数据)
            * @param: @param list
            * @param: @param userProp
            * @param: @return
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2019-04-18
            */
            MessageResponse importXls(List
            <Map
            <String
            , Object>> list, UserProp userProp)throws Exception;


            /**
            *
            * @Title:getList
            * @Description: TODO(条件查询数据)
            * @param: @param p
            * @param: @return
            * @param: @throws Exception
            * @return: ListResult
            <Map
            <String
            ,Object>>
            * @throws
            * @author: huacai003
            * @version: 2019-04-18
            */
            ListResult
            <Map
            <String
            ,Object>> getList(Map
            <String
            , Object> p) throws Exception;


            /**
            * @throws
            * @Title:getListByCondition
            * @Description: TODO(用于控件数据获取)
            * @param: @param params
            * @param: @return
            * @return: Map
            <String
            ,Object>
            * @author: huacai003
            * @version: 2019-04-18
            */
            Map
            <String
            , Object> getListByCondition(Map
            <String
            , Object> params);


            /**
            * @throws
            * @Title:deleteRoadSectionByRoadSectionIds
            * @Description: TODO(批量删除路由器运行状态）
            * @param: @param ids
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @author: huacai003
            * @version: 2019-04-18
            */
            MessageResponse deleteSeRouteStateBySeRouteStateIds(String [] id, UserProp userProp) throws Exception;


            /**
            * @throws
            * @Title:updateStatus
            * @Description: TODO(更新状态)
            * @param: @param obj
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @author: huacai003
            * @version: 2019-04-18
            */
            MessageResponse updateStatus(String id,String status, UserProp userProp) throws Exception;
            }
