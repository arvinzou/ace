package com.huacainfo.ace.gesp.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.BsSmsTask;

/**
 * 业务层
 * 
 * @author yu
 *
 */
public interface  BsSmsTaskService {
	public abstract PageResult<Map<String, Object>> findList(Map<String, Object> condition, int start, int limit, String orderBy) throws Exception;
	public abstract PageResult<Map<String, Object>> findListForSendSMS(Map<String, Object> condition, int start, int limit, String orderBy) throws Exception;
	
	public abstract MessageResponse insert(BsSmsTask obj,UserProp userProp) throws Exception;
	 
	public abstract MessageResponse update(BsSmsTask obj,UserProp userProp) throws Exception;
	
	public abstract MessageResponse insertSelective(BsSmsTask o,UserProp userProp)  throws Exception;
	
	public abstract MessageResponse updateSelective(BsSmsTask o,UserProp userProp) throws Exception;
	 
	public abstract SingleResult<Map<String, Object>> selectByPrimaryKey(String id) throws Exception;
	 
	public abstract MessageResponse deleteById(String id,UserProp userProp) throws Exception;
	 
	public abstract ListResult<Map<String, Object>> findListTop(Map<String, Object> condition, String orderBy) throws Exception;
	
	public abstract ListResult<Map<String, Object>> findListByMember(Map<String, Object> condition, String string);
 
	
}
