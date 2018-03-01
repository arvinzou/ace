package com.huacainfo.ace.portal.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.PageResult;


public interface DataBaseLogService{
	/**
	 * 
	    * @Title:log 
	    * @Description:  TODO(写入业务操作日志，DB日志) 
	 		* @param:        @param log
	 		* @param:        @param name
	 		* @param:        @param old
	 		* @param:        @param news
	 		* @param:        @param type
	 		* @param:        @param userProp    
	 		* @return:       void    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:40:56
	 */
	public  void log(String log,
					 String name,
					 String old,
					 String news,
					 String type,
					 UserProp userProp);
	/**
	 * 
	    * @Title:findList 
	    * @Description:  TODO(分页查询日志) 
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<Map<String,Object>>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:41:20
	 */
	public abstract PageResult<Map<String, Object>> findList(Map<String,Object> condition, int start, int limit, String orderBy) throws Exception;
}
