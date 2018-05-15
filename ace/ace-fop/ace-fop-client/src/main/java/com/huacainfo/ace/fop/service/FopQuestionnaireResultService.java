package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopQuestionnaireResult;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultQVo;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultVo;

/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: TODO(诉求服务)
 */
public interface FopQuestionnaireResultService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诉求服务分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopQuestionnaireResultVo>
     * @author: Arvin
     * @version: 2018-05-11
     */
    PageResult<FopQuestionnaireResultVo> findFopQuestionnaireResultList(FopQuestionnaireResultQVo condition,
                                                                        int start, int limit, String orderBy) throws Exception;

    ResultResponse findQuestionnaireResultList(FopQuestionnaireResultQVo condition,
                                               int page, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopQuestionnaireResult
     * @Description: TODO(添加诉求服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    MessageResponse insertFopQuestionnaireResult(FopQuestionnaireResult obj, UserProp userProp) throws Exception;

    MessageResponse insertQuestionnaireResult(FopQuestionnaireResult obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopQuestionnaireResult
     * @Description: TODO(更新诉求服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    MessageResponse updateFopQuestionnaireResult(FopQuestionnaireResult obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopQuestionnaireResultByPrimaryKey
     * @Description: TODO(获取诉求服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopQuestionnaireResult>
     * @author: Arvin
     * @version: 2018-05-11
     */
    SingleResult<FopQuestionnaireResultVo> selectVoByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopQuestionnaireResultByFopQuestionnaireResultId
     * @Description: TODO(删除诉求服务)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws Exception;

}
