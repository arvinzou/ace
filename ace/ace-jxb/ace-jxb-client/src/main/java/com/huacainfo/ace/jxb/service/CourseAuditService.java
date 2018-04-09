package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CourseAudit;
import com.huacainfo.ace.jxb.vo.CourseAuditVo;
import com.huacainfo.ace.jxb.vo.CourseAuditQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(课程审核记录)
 */
public interface CourseAuditService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(课程审核记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CourseAuditVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<CourseAuditVo> findCourseAuditList(CourseAuditQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertCourseAudit
	    * @Description:  TODO(添加课程审核记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertCourseAudit(CourseAudit obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateCourseAudit
	    * @Description:  TODO(更新课程审核记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateCourseAudit(CourseAudit obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectCourseAuditByPrimaryKey
	    * @Description:  TODO(获取课程审核记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<CourseAudit>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<CourseAuditVo> selectCourseAuditByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteCourseAuditByCourseAuditId
	    * @Description:  TODO(删除课程审核记录)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteCourseAuditByCourseAuditId(String id,UserProp userProp) throws Exception;

	
}
