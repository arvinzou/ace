package com.huacainfo.ace.partyschool.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.Evaluating;
import com.huacainfo.ace.partyschool.vo.EvaluatingVo;
import com.huacainfo.ace.partyschool.vo.EvaluatingQVo;
/**
* @author: 王恩
* @version: 2019-01-03
* @Description:  TODO(评测管理)
*/
public interface EvaluatingService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(评测管理分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<EvaluatingVo>
    * @throws
    * @author: 王恩
    * @version: 2019-01-03
    */
    PageResult
    <EvaluatingVo> findEvaluatingList(EvaluatingQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertEvaluating
        * @Description: TODO(添加评测管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-01-03
        */
        MessageResponse insertEvaluating(Evaluating obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateEvaluating
        * @Description: TODO(更新评测管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-01-03
        */
        MessageResponse updateEvaluating(Evaluating obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectEvaluatingByPrimaryKey
        * @Description: TODO(获取评测管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<Evaluating>
        * @throws
        * @author: 王恩
        * @version: 2019-01-03
        */
        SingleResult
        <EvaluatingVo> selectEvaluatingByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteEvaluatingByEvaluatingId
            * @Description: TODO(删除评测管理)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-01-03
            */
            MessageResponse deleteEvaluatingByEvaluatingId(String id,UserProp userProp) throws Exception;


}