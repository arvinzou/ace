package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.EvaluationRstContent;
import com.huacainfo.ace.partyschool.vo.EvaluationRstContentVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstContentQVo;

/**
 * @author: 王恩
 * @version: 2019-01-08
 * @Description: TODO(测评结果管理)
 */
public interface EvaluationRstContentService {
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
     * <EvaluationRstContentVo>
     * @author: 王恩
     * @version: 2019-01-08
     */
    PageResult
            <EvaluationRstContentVo> findEvaluationRstContentList(EvaluationRstContentQVo condition,
                                                                  int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertEvaluationRstContent
     * @Description: TODO(添加测评结果管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    MessageResponse insertEvaluationRstContent(EvaluationRstContent obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateEvaluationRstContent
     * @Description: TODO(更新测评结果管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    MessageResponse updateEvaluationRstContent(EvaluationRstContent obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectEvaluationRstContentByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationRstContent>
     * @author: 王恩
     * @version: 2019-01-08
     */
    SingleResult
            <EvaluationRstContentVo> selectEvaluationRstContentByPrimaryKey(String id) throws Exception;

    SingleResult
            <EvaluationRstContent> selectEvaluationRstContent(EvaluationRstContent obj) throws Exception;

    /**
     * @throws
     * @Title:deleteEvaluationRstContentByEvaluationRstContentId
     * @Description: TODO(删除测评结果管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    MessageResponse deleteEvaluationRstContentByEvaluationRstContentId(String id, UserProp userProp) throws Exception;

}
