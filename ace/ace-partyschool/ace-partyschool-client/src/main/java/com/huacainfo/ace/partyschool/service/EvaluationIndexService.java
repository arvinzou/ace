package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.EvaluationIndex;
import com.huacainfo.ace.partyschool.vo.EvaluationIndexVo;
import com.huacainfo.ace.partyschool.vo.EvaluationIndexQVo;

/**
 * @author: 王恩
 * @version: 2019-01-04
 * @Description: TODO(评测选项)
 */
public interface EvaluationIndexService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测选项分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluationIndexVo>
     * @author: 王恩
     * @version: 2019-01-04
     */
    PageResult<EvaluationIndexVo> findEvaluationIndexList(EvaluationIndexQVo condition,
                                                        int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertEvaluationIndex
     * @Description: TODO(添加评测选项)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-04
     */
    MessageResponse insertEvaluationIndex(EvaluationIndex obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateEvaluationIndex
     * @Description: TODO(更新评测选项)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-04
     */
    MessageResponse updateEvaluationIndex(EvaluationIndex obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEvaluationIndexByPrimaryKey
     * @Description: TODO(获取评测选项)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationIndex>
     * @author: 王恩
     * @version: 2019-01-04
     */
    SingleResult
            <EvaluationIndexVo> selectEvaluationIndexByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteEvaluationIndexByEvaluationIndexId
     * @Description: TODO(删除评测选项)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-04
     */
    MessageResponse deleteEvaluationIndexByEvaluationIndexId(String id, UserProp userProp) throws Exception;

}
