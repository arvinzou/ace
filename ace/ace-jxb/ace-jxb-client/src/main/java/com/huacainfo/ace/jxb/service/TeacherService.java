package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Teacher;
import com.huacainfo.ace.jxb.vo.TeacherVo;
import com.huacainfo.ace.jxb.vo.TeacherQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(老师)
 */
public interface TeacherService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(老师分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TeacherVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<TeacherVo> findTeacherList(TeacherQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertTeacher
	    * @Description:  TODO(添加老师)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertTeacher(Teacher obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateTeacher
	    * @Description:  TODO(更新老师)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateTeacher(Teacher obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectTeacherByPrimaryKey
	    * @Description:  TODO(获取老师)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Teacher>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<TeacherVo> selectTeacherByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteTeacherByTeacherId
	    * @Description:  TODO(删除老师)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteTeacherByTeacherId(String id,UserProp userProp) throws Exception;

	
}
