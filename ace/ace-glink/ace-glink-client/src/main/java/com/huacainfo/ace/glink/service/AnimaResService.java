package com.huacainfo.ace.glink.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.glink.model.AnimaRes;
import com.huacainfo.ace.glink.vo.AnimaResVo;
import com.huacainfo.ace.glink.vo.AnimaResQVo;
import java.util.Map;
import java.util.List;
/**
* @author: luocan
* @version: 2019-04-10
* @Description:  TODO(节目管理)
*/
public interface AnimaResService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(节目管理分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<AnimaResVo>
    * @throws
    * @author: luocan
    * @version: 2019-04-10
    */
    PageResult
    <AnimaResVo> findAnimaResList(AnimaResQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertAnimaRes
        * @Description: TODO(添加节目管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: luocan
        * @version: 2019-04-10
        */
        MessageResponse insertAnimaRes(AnimaRes obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateAnimaRes
        * @Description: TODO(更新节目管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: luocan
        * @version: 2019-04-10
        */
        MessageResponse updateAnimaRes(AnimaRes obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectAnimaResByPrimaryKey
        * @Description: TODO(获取节目管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<AnimaRes>
        * @throws
        * @author: luocan
        * @version: 2019-04-10
        */
        SingleResult
        <AnimaResVo> selectAnimaResByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteAnimaResByAnimaResId
            * @Description: TODO(删除节目管理)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: luocan
            * @version: 2019-04-10
            */
            MessageResponse deleteAnimaResByAnimaResId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核节目管理)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: luocan
            * @version: 2019-04-10
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
            * @version: 2019-04-10
            */
            MessageResponse importXls(List
            <Map
            <String
            , Object>> list, UserProp userProp)throws Exception;

            /**
            * @throws
            * @Title:updateStatus
            * @Description: TODO(更新状态)
            * @param: @param obj
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @author: luocan
            * @version: 2019-04-10
            */
            MessageResponse updateStatus(String id,String status, UserProp userProp) throws Exception;
            }
