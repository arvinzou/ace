package com.huacainfo.ace.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.dao.TaskMapper;
import com.huacainfo.ace.portal.model.Task;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.TaskService;
import com.huacainfo.ace.portal.vo.TaskVo;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public PageResult<TaskVo> findTaskList(Task condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<TaskVo> rst = new PageResult<TaskVo>();
		List<TaskVo> list = this.taskMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.taskMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertTask(Task o, UserProp userProp)
			throws Exception {
		
		/*if (CommonUtils.isBlank(o.getTaskId())) {
			return new SingleResult(false, "员工编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getTitle())) {
			return new SingleResult(false, "姓名不能为空！");
		}
		if (CommonUtils.isBlank(o.getSex())) {
			return new SingleResult(false, "性别不能为空！");
		}
		if (CommonUtils.isBlank(o.getIdCard())) {
			return new SingleResult(false, "身份证号不能为空！");
		}
		
		int temp = this.taskMapper.isExitByIdCard(o.getIdCard());
		if (temp > 0) {
			return new SingleResult(false, "已存在此身份证的数据！");
		}*/
		this.taskMapper.insert(o);
		this.dataBaseLogService.log("添加教职工", "教职工", "", o.getTitle(),
				o.getTitle(), userProp);
		return new MessageResponse(0, "添加教职工完成！");
	}

	public MessageResponse updateTask(Task o, UserProp userProp)
			throws Exception {
	
		/*if (CommonUtils.isBlank(o.getTaskId())) {
			return new SingleResult(false, "员工编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getTitle())) {
			return new SingleResult(false, "姓名不能为空！");
		}
		if (CommonUtils.isBlank(o.getSex())) {
			return new SingleResult(false, "性别不能为空！");
		}
		if (CommonUtils.isBlank(o.getIdCard())) {
			return new SingleResult(false, "身份证号不能为空！");
		}*/
		
		this.taskMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更教职工", "教职工", "", o.getTitle(),
				o.getTitle(), userProp);
		return new MessageResponse(0, "变更教职工完成！");
	}

	public SingleResult<Task> selectTaskByPrimaryKey(String id)
			throws Exception {
		SingleResult<Task> rst = new SingleResult<Task>();
		rst.setValue(this.taskMapper.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteTaskByTaskId(String id,
			UserProp userProp) throws Exception {
		MessageResponse rst = new MessageResponse();
		this.taskMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除教职工", "教职工", String.valueOf(id), String.valueOf(id),
				"教职工", userProp);
		return rst;
	}
	public  ListResult<Task> findListByUserId(UserProp userProp)  throws Exception{
		ListResult<Task> rst = new ListResult<Task>();
		List<Task> list=this.taskMapper.findListByUserId(userProp.getUserId());
		rst.setValue(list);
		return rst;
	}

}
