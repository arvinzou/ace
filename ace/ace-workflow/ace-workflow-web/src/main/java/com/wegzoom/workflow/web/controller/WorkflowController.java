package com.huacainfo.workflow.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.workflow.service.WorkflowService;
import com.huacainfo.ace.workflow.vo.ProcessDefinitionVo;
import com.huacainfo.ace.workflow.vo.ProcessInstanceVo;

@Controller
@RequestMapping("/workflow")
public class WorkflowController extends WorkflowBaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IFile fileSaver;
	@Autowired
	private WorkflowService workflowService;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/findWorkflowList.do")
	@ResponseBody
	public PageResult<ProcessDefinitionVo> findWorkflowList(String name,
			String category, int start, int limit) throws Exception {
		return this.workflowService.findWorkflowList(name, category, start,
				limit);
	}

	@RequestMapping(value = "/deleteWorkflowById.do")
	@ResponseBody
	public MessageResponse deleteWorkflowById(String deploymentId)
			throws Exception {
		return this.workflowService.deleteWorkflowById(deploymentId,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/getStartFormKey.do")
	@ResponseBody
	public SingleResult<String> getStartFormKey(String processDefinitionId,
			UserProp userProp) throws Exception {
		return this.workflowService.getStartFormKey(processDefinitionId,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/startProcessInstanceById.do")
	@ResponseBody
	public SingleResult<ProcessInstanceVo> startProcessInstanceById(
			String processDefinitionId) throws Exception {
		return this.workflowService.startProcessInstanceById(
				processDefinitionId, this.getParams(), this.getCurUserProp());
	}

	@RequestMapping(value = "/completeTask.do")
	@ResponseBody
	public MessageResponse completeTask(String taskId) throws Exception {
		return this.workflowService.completeTask(taskId, this.getParams(),
				this.getCurUserProp());
	}

	@RequestMapping(value = "/findPersonalTasks.do")
	@ResponseBody
	public MessageResponse findPersonalTasks() throws Exception {
		return this.workflowService.findPersonalTasks(this.getCurUserProp());
	}

	@RequestMapping(value = "/findProcessInstanceList.do")
	@ResponseBody
	public PageResult<Map<String, Object>> findProcessInstanceList(
			PageParamNoChangeSord page) throws Exception {
		Map<String,Object> params=this.getParams();
		params.put("userId", this.getCurUserProp().getUserId());
		PageResult<Map<String, Object>> rst = this.workflowService
				.findProcessInstanceList(params, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}
	@RequestMapping(value = "/getModelJSON.do")
	@ResponseBody
	public JSON getModelJSON(String processInstanceId) throws Exception{
		String text=this.workflowService.getModelJSON(processInstanceId);
		JSON rst=JSON.parseObject(text);
		return rst;
	}
}
