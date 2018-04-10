package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.ExamScore;
import com.huacainfo.ace.jxb.vo.ExamScoreVo;
import com.huacainfo.ace.jxb.vo.ExamScoreQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(评测)
 */
public interface ExamScoreService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ExamScoreVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<ExamScoreVo> findExamScoreList(ExamScoreQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertExamScore
	    * @Description:  TODO(添加评测)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertExamScore(ExamScore obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateExamScore
	    * @Description:  TODO(更新评测)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateExamScore(ExamScore obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectExamScoreByPrimaryKey
	    * @Description:  TODO(获取评测)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ExamScore>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<ExamScoreVo> selectExamScoreByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteExamScoreByExamScoreId
	    * @Description:  TODO(删除评测)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteExamScoreByExamScoreId(String id,UserProp userProp) throws Exception;

	
}
