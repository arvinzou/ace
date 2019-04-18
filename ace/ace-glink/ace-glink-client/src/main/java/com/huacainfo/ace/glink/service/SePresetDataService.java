package com.huacainfo.ace.glink.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.SePresetData;
import com.huacainfo.ace.glink.vo.SePresetDataVo;
import com.huacainfo.ace.glink.vo.SePresetDataQVo;
import java.util.Map;
import java.util.List;
/**
* @author: huacai003
* @version: 2019-04-18
* @Description:  TODO(场景定义数据)
*/
public interface SePresetDataService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(场景定义数据分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<SePresetDataVo>
    * @throws
    * @author: huacai003
    * @version: 2019-04-18
    */
    PageResult
    <SePresetDataVo> findSePresetDataList(SePresetDataQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertSePresetData
        * @Description: TODO(添加场景定义数据)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: huacai003
        * @version: 2019-04-18
        */
        MessageResponse insertSePresetData(SePresetData obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateSePresetData
        * @Description: TODO(更新场景定义数据)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: huacai003
        * @version: 2019-04-18
        */
        MessageResponse updateSePresetData(SePresetData obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectSePresetDataByPrimaryKey
        * @Description: TODO(获取场景定义数据)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<SePresetData>
        * @throws
        * @author: huacai003
        * @version: 2019-04-18
        */
        SingleResult
        <SePresetDataVo> selectSePresetDataByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteSePresetDataBySePresetDataId
            * @Description: TODO(删除场景定义数据)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: huacai003
            * @version: 2019-04-18
            */
            MessageResponse deleteSePresetDataBySePresetDataId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核场景定义数据)
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
            * @Description: TODO(批量删除场景定义数据）
            * @param: @param ids
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @author: huacai003
            * @version: 2019-04-18
            */
            MessageResponse deleteSePresetDataBySePresetDataIds(String [] id, UserProp userProp) throws Exception;


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
