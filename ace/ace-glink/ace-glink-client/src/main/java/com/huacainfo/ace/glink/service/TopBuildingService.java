package com.huacainfo.ace.glink.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.TopBuilding;
import com.huacainfo.ace.glink.vo.TopBuildingVo;
import com.huacainfo.ace.glink.vo.TopBuildingQVo;
import java.util.Map;
import java.util.List;
/**
* @author: luocan
* @version: 2019-04-09
* @Description:  TODO(建筑物管理)
*/
public interface TopBuildingService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(建筑物管理分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<TopBuildingVo>
    * @throws
    * @author: luocan
    * @version: 2019-04-09
    */
    PageResult
    <TopBuildingVo> findTopBuildingList(TopBuildingQVo condition,int start, int limit, String orderBy) throws
        Exception;

        /**
        *
        * @Title:insertTopBuilding
        * @Description: TODO(添加建筑物管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: luocan
        * @version: 2019-04-09
        */
        MessageResponse insertTopBuilding(TopBuilding obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateTopBuilding
        * @Description: TODO(更新建筑物管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: luocan
        * @version: 2019-04-09
        */
        MessageResponse updateTopBuilding(TopBuilding obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectTopBuildingByPrimaryKey
        * @Description: TODO(获取建筑物管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<TopBuilding>
        * @throws
        * @author: luocan
        * @version: 2019-04-09
        */
        SingleResult
        <TopBuildingVo> selectTopBuildingByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteTopBuildingByTopBuildingId
            * @Description: TODO(删除建筑物管理)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: luocan
            * @version: 2019-04-09
            */
            MessageResponse deleteTopBuildingByTopBuildingId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核建筑物管理)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: luocan
            * @version: 2019-04-09
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
            * @author: luocan
            * @version: 2019-04-09
            */
            public MessageResponse importXls(List
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
            * @author: luocan
            * @version: 2019-04-09
            */
            public ListResult
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
            * @author: luocan
            * @version: 2019-04-09
            */
            public Map
            <String
            , Object> getListByCondition(Map
            <String
            , Object> params);


            /**
            * @throws
            * @Title:deleteRoadSectionByRoadSectionIds
            * @Description: TODO(批量删除建筑物管理）
            * @param: @param ids
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @author: luocan
            * @version: 2019-04-09
            */
            public MessageResponse deleteTopBuildingByTopBuildingIds(String [] id, UserProp userProp) throws
            Exception;


            /**
            * @throws
            * @Title:updateStatus
            * @Description: TODO(更新状态)
            * @param: @param obj
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @author: luocan
            * @version: 2019-04-09
            */
            MessageResponse updateStatus(String id,String status, UserProp userProp) throws Exception;
            }
