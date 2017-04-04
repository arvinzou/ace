package com.huacainfo.ace.workflow.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;

public interface ActFormFieldService {
	public abstract MessageResponse saveOrUpdateActFormField(String jsons,String formId,
			UserProp userProp) throws Exception;

	public abstract Map<String,Object> selectActFormFieldByPrimaryKey(String id)
			throws Exception;
	

	public abstract MessageResponse deleteActFormFieldByActFormFieldId(
			String id, UserProp userProp) throws Exception;
}
