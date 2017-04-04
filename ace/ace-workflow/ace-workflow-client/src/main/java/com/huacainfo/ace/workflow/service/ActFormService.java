package com.huacainfo.ace.workflow.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;

public interface ActFormService {
	

	
	public MessageResponse saveOrUpdateActForm(String jsons, UserProp userProp)
			throws Exception ;
	
	public abstract MessageResponse deleteActFormFieldByActFormFieldId(
			String id,String fieldType, UserProp userProp) throws Exception;
	
	public abstract ListResult<Map<String,Object>> selectDefFormByFormId(
			String id,UserProp userProp) throws Exception;
	
	public MessageResponse saveOrUpdateFormData(Map<String,Object> params,String appId,String instId,String formId,String taskId,UserProp userProp) throws Exception;
	
	public abstract ListResult<Map<String,Object>> selectListByInstId(
			String instId,UserProp userProp) throws Exception;
	
	public abstract ListResult<Map<String,Object>> selectModelByAppId(
			String appId,UserProp userProp) throws Exception;
}
