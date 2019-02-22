package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.EvaluationRst;
import com.huacainfo.ace.partyschool.model.EvaluationRstContent;
import com.huacainfo.ace.partyschool.vo.EvaluationRstVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstQVo;

import java.util.List;
import java.util.Map;

/**
 * @author: 王恩
 * @version: 2019-01-08
 * @Description: TODO(测评结果管理)
 */
public interface EvaluationRstService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluationRstVo>
     * @author: 王恩
     * @version: 2019-01-08
     */
    PageResult<EvaluationRstVo> findEvaluationRstList(EvaluationRstQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    PageResult<EvaluationRstVo> findEvaluationRstListVo(EvaluationRstQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertEvaluationRst
     * @Description: TODO(添加测评结果管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    MessageResponse insertEvaluationRst(EvaluationRst obj, UserProp userProp) throws Exception;

    ResultResponse insertEvaluationRstList(List<EvaluationRst> list, EvaluationRstContent obj, UserProp userProp) throws Exception;
    SingleResult<List<Map<String,String>>> exportData(String id) throws Exception;

    /**
     * @throws
     * @Title:updateEvaluationRst
     * @Description: TODO(更新测评结果管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    MessageResponse updateEvaluationRst(EvaluationRst obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEvaluationRstByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationRst>
     * @author: 王恩
     * @version: 2019-01-08
     */
    SingleResult<EvaluationRstVo> selectEvaluationRstByPrimaryKey(String id) throws Exception;

    ResultResponse statistics(String classScheduleId) throws Exception;

    /**
     * @throws
     * @Title:deleteEvaluationRstByEvaluationRstId
     * @Description: TODO(删除测评结果管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    MessageResponse deleteEvaluationRstByEvaluationRstId(String id, UserProp userProp) throws Exception;

}
