package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.ExamScoreSub;
import com.huacainfo.ace.jxb.vo.ExamScoreSubVo;
import com.huacainfo.ace.jxb.vo.ExamScoreSubQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(评测结果)
 */
public interface ExamScoreSubService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测结果分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ExamScoreSubVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<ExamScoreSubVo> findExamScoreSubList(ExamScoreSubQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertExamScoreSub
	    * @Description:  TODO(添加评测结果)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertExamScoreSub(ExamScoreSub obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateExamScoreSub
	    * @Description:  TODO(更新评测结果)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateExamScoreSub(ExamScoreSub obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectExamScoreSubByPrimaryKey
	    * @Description:  TODO(获取评测结果)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ExamScoreSub>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<ExamScoreSubVo> selectExamScoreSubByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteExamScoreSubByExamScoreSubId
	    * @Description:  TODO(删除评测结果)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteExamScoreSubByExamScoreSubId(String id,UserProp userProp) throws Exception;

	
}
