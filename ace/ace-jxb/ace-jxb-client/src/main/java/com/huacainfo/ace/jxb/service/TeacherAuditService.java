package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.vo.TeacherAuditVo;
import com.huacainfo.ace.jxb.vo.TeacherAuditQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(老师审核记录)
 */
public interface TeacherAuditService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(老师审核记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TeacherAuditVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<TeacherAuditVo> findTeacherAuditList(TeacherAuditQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertTeacherAudit
	    * @Description:  TODO(添加老师审核记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertTeacherAudit(TeacherAudit obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateTeacherAudit
	    * @Description:  TODO(更新老师审核记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateTeacherAudit(TeacherAudit obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectTeacherAuditByPrimaryKey
	    * @Description:  TODO(获取老师审核记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TeacherAudit>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<TeacherAuditVo> selectTeacherAuditByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteTeacherAuditByTeacherAuditId
	    * @Description:  TODO(删除老师审核记录)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteTeacherAuditByTeacherAuditId(String id,UserProp userProp) throws Exception;

	
}
