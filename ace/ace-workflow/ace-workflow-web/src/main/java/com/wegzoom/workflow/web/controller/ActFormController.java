package com.huacainfo.workflow.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.workflow.service.ActFormService;

@Controller
@RequestMapping("/actForm")
public class ActFormController extends WorkflowBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActFormService actFormService;

	
	@RequestMapping(value = "/saveOrUpdateActForm.do")
	@ResponseBody
	public MessageResponse saveOrUpdateActForm(String jsons) throws Exception {
		return this.actFormService
				.saveOrUpdateActForm(jsons, this.getCurUserProp());
	}
	
	@RequestMapping(value = "/deleteActFormFieldByActFormFieldId.do")
	@ResponseBody
	public MessageResponse deleteActFormFieldByActFormFieldId(String id,String fieldType) throws Exception {
		return this.actFormService
				.deleteActFormFieldByActFormFieldId(id,fieldType, this.getCurUserProp());
	}
	@RequestMapping(value = "/selectDefFormByFormId.do")
	@ResponseBody
	public MessageResponse selectDefFormByFormId(String formId) throws Exception {
		return this.actFormService
				.selectDefFormByFormId(formId, this.getCurUserProp());
	}
	
	@RequestMapping(value = "/selectListByInstId.do")
	@ResponseBody
	public MessageResponse selectListByInstId(String instId) throws Exception {
		return this.actFormService
				.selectListByInstId(instId, this.getCurUserProp());
	}
	@RequestMapping(value = "/selectModelByAppId.do")
	@ResponseBody
	public MessageResponse selectModelByAppId(String appId) throws Exception {
		return this.actFormService
				.selectModelByAppId(appId, this.getCurUserProp());
	}

}
