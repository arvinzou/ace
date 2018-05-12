package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopQuestion;
import com.huacainfo.ace.fop.vo.FopQuestionVo;
import com.huacainfo.ace.fop.vo.FopQuestionQVo;

import java.util.List;

/**
 * @author: Arvin
 * @version: 2018-05-07
 * @Description: TODO(法律帮助/政府诉求)
 */
public interface FopQuestionService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(法律帮助/政府诉求分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopQuestionVo>
     * @author: Arvin
     * @version: 2018-05-07
     */
    public abstract PageResult<FopQuestionVo> findFopQuestionList(FopQuestionQVo condition, int start, int limit, String orderBy) throws Exception;

    public abstract ResultResponse findQuestionList(FopQuestionQVo condition, int page, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopQuestion
     * @Description: TODO(添加法律帮助/政府诉求)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    public abstract MessageResponse insertFopQuestion(FopQuestion obj, UserProp userProp) throws Exception;


    public abstract MessageResponse insertQuestion(FopQuestion obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopQuestion
     * @Description: TODO(更新法律帮助/政府诉求)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    public abstract MessageResponse updateFopQuestion(FopQuestion obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopQuestionByPrimaryKey
     * @Description: TODO(获取法律帮助/政府诉求)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopQuestion>
     * @author: Arvin
     * @version: 2018-05-07
     */
    public abstract SingleResult
            <FopQuestionVo> selectFopQuestionByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopQuestionByFopQuestionId
     * @Description: TODO(删除法律帮助/政府诉求)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    public abstract MessageResponse deleteFopQuestionByFopQuestionId(String id, UserProp userProp) throws
            Exception;


    public abstract List<FopQuestionVo> findCommentList(String parentId) throws Exception;


}
