package com.huacainfo.ace.portal.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Task;
import com.huacainfo.ace.portal.service.TaskService;
import com.huacainfo.ace.portal.vo.TaskVo;

@Controller
@RequestMapping("/task")
public class TaskController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TaskService taskService;
	/**
	 * 
	    * @Title:findTaskList 
	    * @Description:  TODO(任务分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<TaskVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:40:45
	 */
	@RequestMapping(value = "/findTaskList.do")
	@ResponseBody
	public PageResult<TaskVo> findTaskList(Task condition, PageParam page)
			throws Exception {
		PageResult<TaskVo> rst = this.taskService.findTaskList(condition,
				page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}
	/**
	 * 
	    * @Title:insertTask 
	    * @Description:  TODO(添加任务) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:41:14
	 */
	@RequestMapping(value = "/insertTask.do")
	@ResponseBody
	public MessageResponse insertTask(String jsons) throws Exception {
		Task obj = JSON.parseObject(jsons, Task.class);
		return this.taskService.insertTask(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateTask 
	    * @Description:  TODO(更新任务) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:41:25
	 */
	@RequestMapping(value = "/updateTask.do")
	@ResponseBody
	public MessageResponse updateTask(String jsons) throws Exception {
		Task obj = JSON.parseObject(jsons, Task.class);
		return this.taskService.updateTask(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:selectTaskByPrimaryKey 
	    * @Description:  TODO(根据主键获取任务信息) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<Task>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:41:38
	 */
	@RequestMapping(value = "/selectTaskByPrimaryKey.do")
	@ResponseBody
	public SingleResult<Task> selectTaskByPrimaryKey(String id)
			throws Exception {
		return this.taskService.selectTaskByPrimaryKey(id);
	}
	/**
	 * 
	    * @Title:deleteTaskByTaskId 
	    * @Description:  TODO(删除任务信息) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:42:00
	 */
	@RequestMapping(value = "/deleteTaskByTaskId.do")
	@ResponseBody
	public MessageResponse deleteTaskByTaskId(String id) throws Exception {
		return this.taskService.deleteTaskByTaskId(id, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:findListByUserId 
	    * @Description:  TODO(获取用户任务列表) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ListResult<Task>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:42:44
	 */
	@RequestMapping(value = "/findListByUserId.do")
	@ResponseBody
	public ListResult<Task> findListByUserId() throws Exception {
		return this.taskService.findListByUserId(this.getCurUserProp());
	}
}
