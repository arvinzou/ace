package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopAiQuestion;
import com.huacainfo.ace.fop.vo.FopAiQuestionQVo;
import com.huacainfo.ace.fop.vo.FopAiQuestionVo;

/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: TODO(诉求服务)
 */
public interface FopAiQuestionService {
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
     * <FopAiQuestionVo>
     * @author: Arvin
     * @version: 2018-05-11
     */
    PageResult<FopAiQuestionVo> findFopAiQuestionList(FopAiQuestionQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopAiQuestion
     * @Description: TODO(添加诉求服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    MessageResponse insertFopAiQuestion(FopAiQuestion obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopAiQuestion
     * @Description: TODO(更新诉求服务)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    MessageResponse updateFopAiQuestion(FopAiQuestion obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopAiQuestionByPrimaryKey
     * @Description: TODO(获取诉求服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopAiQuestion>
     * @author: Arvin
     * @version: 2018-05-11
     */
    SingleResult<FopAiQuestionVo> selectFopAiQuestionByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopAiQuestionByFopAiQuestionId
     * @Description: TODO(删除诉求服务)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    MessageResponse deleteFopAiQuestionByFopAiQuestionId(String id, UserProp userProp) throws Exception;

}
