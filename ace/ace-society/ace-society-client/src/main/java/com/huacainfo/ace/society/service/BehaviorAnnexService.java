package com.huacainfo.ace.society.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.BehaviorAnnex;
import com.huacainfo.ace.society.vo.BehaviorAnnexVo;
import com.huacainfo.ace.society.vo.BehaviorAnnexQVo;
/**
* @author: lcan
* @version: 2018-09-12
* @Description:  TODO(市民文明随手拍附件)
*/
public interface BehaviorAnnexService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(市民文明随手拍附件分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<BehaviorAnnexVo>
    * @throws
    * @author: lcan
    * @version: 2018-09-12
    */
    PageResult
    <BehaviorAnnexVo> findBehaviorAnnexList(BehaviorAnnexQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertBehaviorAnnex
        * @Description: TODO(添加市民文明随手拍附件)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: lcan
        * @version: 2018-09-12
        */
        MessageResponse insertBehaviorAnnex(BehaviorAnnex obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateBehaviorAnnex
        * @Description: TODO(更新市民文明随手拍附件)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: lcan
        * @version: 2018-09-12
        */
        MessageResponse updateBehaviorAnnex(BehaviorAnnex obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectBehaviorAnnexByPrimaryKey
        * @Description: TODO(获取市民文明随手拍附件)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<BehaviorAnnex>
        * @throws
        * @author: lcan
        * @version: 2018-09-12
        */
        SingleResult
        <BehaviorAnnexVo> selectBehaviorAnnexByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteBehaviorAnnexByBehaviorAnnexId
            * @Description: TODO(删除市民文明随手拍附件)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: lcan
            * @version: 2018-09-12
            */
            MessageResponse deleteBehaviorAnnexByBehaviorAnnexId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核市民文明随手拍附件)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: lcan
            * @version: 2018-09-12
            */
            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;
            }
