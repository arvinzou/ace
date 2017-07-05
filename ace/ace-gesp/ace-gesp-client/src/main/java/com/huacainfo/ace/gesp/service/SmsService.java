package com.huacainfo.ace.gesp.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.Message;
import com.huacainfo.ace.gesp.model.Sms;

/**
 * 业务层
 * 
 * @author yu
 *
 */
public interface  SmsService {
	public abstract PageResult<Map<String, Object>> findList(Map<String, Object> condition, int start, int limit, String orderBy) throws Exception;
	 
	public abstract MessageResponse insert(Sms obj, UserProp userProp) throws Exception;
	 
	public abstract MessageResponse update(Sms obj, UserProp userProp) throws Exception;
	
	public abstract MessageResponse insertSelective(Sms o, UserProp userProp)  throws Exception;
	
	public abstract MessageResponse updateSelective(Sms o, UserProp userProp) throws Exception;
	 
	public abstract SingleResult<Map<String, Object>> selectByPrimaryKey(String id) throws Exception;
	 
	public abstract MessageResponse deleteById(String id, UserProp userProp) throws Exception;
	 
	public abstract ListResult<Map<String, Object>> findListTop(Map<String, Object> condition, String orderBy) throws Exception;

	public abstract MessageResponse insertBatch(List<Sms> list, UserProp curUserProp);

	public abstract MessageResponse insertSelectiveByBussId(String oldTaskId, String newTaskId);
 
	
}
