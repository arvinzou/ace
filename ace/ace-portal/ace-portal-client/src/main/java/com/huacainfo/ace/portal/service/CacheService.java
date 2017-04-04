package com.huacainfo.ace.portal.service;

public interface CacheService {
	/**
	 * 
	    * @Title:get 
	    * @Description:  TODO(根据KEY获取对象) 
	 		* @param:        @param key
	 		* @param:        @return    
	 		* @return:       Object    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:12:23
	 */
	public abstract Object get(String key);
	/**
	 * 
	    * @Title:put 
	    * @Description:  TODO(更具KEY存储对象) 
	 		* @param:        @param key
	 		* @param:        @param value    
	 		* @return:       void    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:12:41
	 */
	public abstract void put(String key,Object value);
	/**
	 * 
	    * @Title:containsKey 
	    * @Description:  TODO(判断是否存在KEY) 
	 		* @param:        @param key
	 		* @param:        @return    
	 		* @return:       boolean    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:13:01
	 */
	public abstract boolean containsKey(String key);
	/**
	 * 
	    * @Title:clear 
	    * @Description:  TODO(清除缓存) 
	 		* @param:            
	 		* @return:       void    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:13:23
	 */
	
	public abstract void clear();
	/**
	 * 
	    * @Title:init 
	    * @Description:  TODO(初始化) 
	 		* @param:            
	 		* @return:       void    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午3:13:36
	 */
	
	public abstract void init();
}
