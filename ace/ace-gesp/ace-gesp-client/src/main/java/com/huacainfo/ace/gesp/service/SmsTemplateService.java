package com.huacainfo.ace.gesp.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.Sms;
import com.huacainfo.ace.gesp.model.SmsTemplate;

/**
 * 消息业务层
 * 
 * @author yu
 *
 */
public interface SmsTemplateService {
	public abstract PageResult<Map<String, Object>> findList(Map<String, Object> condition, int start, int limit, String orderBy) throws Exception;
	 
	public abstract MessageResponse insert(SmsTemplate obj, UserProp userProp) throws Exception;
	 
	public abstract MessageResponse update(SmsTemplate obj, UserProp userProp) throws Exception;
	
	public abstract MessageResponse insertSelective(SmsTemplate o, UserProp userProp)  throws Exception;
	
	public abstract MessageResponse updateSelective(SmsTemplate o, UserProp userProp) throws Exception;
	 
	public abstract SingleResult<Map<String, Object>> selectByPrimaryKey(String id) throws Exception;
	 
	public abstract MessageResponse deleteById(String id, UserProp userProp) throws Exception;
	 
	public abstract ListResult<Map<String, Object>> findListTop(Map<String, Object> condition, String orderBy) throws Exception;
}
