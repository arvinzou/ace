package com.huacainfo.ace.workflow.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.workflow.vo.ProcessDefinitionVo;
import com.huacainfo.ace.workflow.vo.ProcessInstanceVo;
import com.huacainfo.ace.workflow.vo.TaskVo;
import com.fasterxml.jackson.databind.JsonNode;
/**
 * @author 陈晓克
 * @version 2013-12-23 上午11:27:54
 */
public interface WorkflowService {

	public MessageResponse deploy(String[] file, String id, UserProp userProp)
			throws Exception;

	public PageResult<ProcessDefinitionVo> findWorkflowList(String name,
			String category, int start, int limit) throws Exception;

	public SingleResult<String> getStartFormKey(String processDefinitionId,
			UserProp userProp) throws Exception;

	public MessageResponse deleteWorkflowById(String deploymentId,
			UserProp userProp) throws Exception;

	public SingleResult<ProcessInstanceVo> startProcessInstanceById(
			String processDefinitionId, Map<String, Object> params,
			UserProp userProp) throws Exception;

	public MessageResponse completeTask(String taskId,
			Map<String, Object> variables, UserProp userProp) throws Exception;

	public ListResult<TaskVo> findPersonalTasks(UserProp userProp)
			throws Exception;

	public ListResult<TaskVo> findTaskByProcessInstanceId(String instanceId)
			throws Exception;

	public ListResult<TaskVo> findHistoryTaskByProcessInstanceId(
			String instanceId) throws Exception;

	public abstract PageResult<Map<String, Object>> findProcessInstanceList(
			Map<String, Object> condition, int start, int limit, String orderBy)
			throws Exception;
	
	public String getModelJSON(String processInstanceId) throws Exception;

}
