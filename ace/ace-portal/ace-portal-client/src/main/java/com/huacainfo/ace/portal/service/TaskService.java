package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Task;
import com.huacainfo.ace.portal.vo.TaskVo;

public interface TaskService {
	/**
	 * 
	    * @Title:findTaskList 
	    * @Description:  TODO(任务分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<TaskVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:28:03
	 */
	public abstract PageResult<TaskVo> findTaskList(Task condition, int start, int limit, String orderBy) throws Exception;
	/**
	 * 
	    * @Title:insertTask 
	    * @Description:  TODO(添加任务) 
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:28:15
	 */
	public abstract MessageResponse insertTask(Task obj,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:updateTask 
	    * @Description:  TODO(更新任务) 
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:28:26
	 */
	public abstract MessageResponse updateTask(Task obj,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:selectTaskByPrimaryKey 
	    * @Description:  TODO(获取任务信息) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<Task>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:28:37
	 */
	public abstract SingleResult<Task> selectTaskByPrimaryKey(String id) throws Exception;
	/**
	 * 
	    * @Title:deleteTaskByTaskId 
	    * @Description:  TODO(删除任务) 
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:28:47
	 */
	public abstract MessageResponse deleteTaskByTaskId(String id,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:findListByUserId 
	    * @Description:  TODO(获取个人任务列表) 
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ListResult<Task>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:28:56
	 */
	public abstract ListResult<Task> findListByUserId(UserProp userProp)  throws Exception;
}
