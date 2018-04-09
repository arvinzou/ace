package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Question;
import com.huacainfo.ace.jxb.vo.QuestionVo;
import com.huacainfo.ace.jxb.vo.QuestionQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(考题)
 */
public interface QuestionService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(考题分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<QuestionVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<QuestionVo> findQuestionList(QuestionQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertQuestion
	    * @Description:  TODO(添加考题)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertQuestion(Question obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateQuestion
	    * @Description:  TODO(更新考题)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateQuestion(Question obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectQuestionByPrimaryKey
	    * @Description:  TODO(获取考题)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Question>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<QuestionVo> selectQuestionByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteQuestionByQuestionId
	    * @Description:  TODO(删除考题)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteQuestionByQuestionId(String id,UserProp userProp) throws Exception;

	
}
