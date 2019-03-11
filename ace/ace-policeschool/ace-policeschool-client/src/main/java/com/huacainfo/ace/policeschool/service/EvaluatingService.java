package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.policeschool.model.Evaluating;
import com.huacainfo.ace.policeschool.vo.EvaluatingQVo;
import com.huacainfo.ace.policeschool.vo.EvaluatingVo;

/**
 * @author: 王恩
 * @version: 2019-01-03
 * @Description: TODO(评测管理)
 */
public interface EvaluatingService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluatingVo>
     * @author: 王恩
     * @version: 2019-01-03
     */
    PageResult<EvaluatingVo> findEvaluatingList(EvaluatingQVo condition,
                                                int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertEvaluating
     * @Description: TODO(添加评测管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-03
     */
    MessageResponse insertEvaluating(Evaluating obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateEvaluating
     * @Description: TODO(更新评测管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-03
     */
    MessageResponse updateEvaluating(Evaluating obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEvaluatingByPrimaryKey
     * @Description: TODO(获取评测管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Evaluating>
     * @author: 王恩
     * @version: 2019-01-03
     */
    SingleResult<EvaluatingVo> selectEvaluatingByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteEvaluatingByEvaluatingId
     * @Description: TODO(删除评测管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-03
     */
    MessageResponse deleteEvaluatingByEvaluatingId(String id, UserProp userProp) throws Exception;

    MessageResponse softdel(String id, UserProp userProp) throws Exception;

}