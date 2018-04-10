package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CourseCmt;
import com.huacainfo.ace.jxb.vo.CourseCmtVo;
import com.huacainfo.ace.jxb.vo.CourseCmtQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(课程评论)
 */
public interface CourseCmtService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(课程评论分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CourseCmtVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<CourseCmtVo> findCourseCmtList(CourseCmtQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertCourseCmt
	    * @Description:  TODO(添加课程评论)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertCourseCmt(CourseCmt obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateCourseCmt
	    * @Description:  TODO(更新课程评论)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateCourseCmt(CourseCmt obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectCourseCmtByPrimaryKey
	    * @Description:  TODO(获取课程评论)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<CourseCmt>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<CourseCmtVo> selectCourseCmtByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteCourseCmtByCourseCmtId
	    * @Description:  TODO(删除课程评论)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteCourseCmtByCourseCmtId(String id,UserProp userProp) throws Exception;

	
}
