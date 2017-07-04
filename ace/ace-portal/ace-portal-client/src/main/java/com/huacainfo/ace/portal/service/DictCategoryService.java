package com.huacainfo.ace.portal.service;

import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.DictCategory;

public interface DictCategoryService {
	/**
	 * 
	    * @Title:findDictCategoryList 
	    * @Description:  TODO(字典类别分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<DictCategory>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午9:05:38
	 */
	public abstract PageResult<DictCategory> findDictCategoryList(DictCategory condition, int start, int limit, String orderBy) throws Exception;
	/**
	 * 
	    * @Title:insertDictCategory 
	    * @Description:  TODO(添加字典类型) 
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午9:06:13
	 */
	public abstract MessageResponse insertDictCategory(DictCategory obj,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:updateDictCategory 
	    * @Description:  TODO(更新字典类型) 
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午9:06:37
	 */
	public abstract MessageResponse updateDictCategory(DictCategory obj,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:selectDictCategoryByPrimaryKey 
	    * @Description:  TODO(根据主键获取一个字典类型数据) 
	 		* @param:        @param DictCategoryId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<DictCategory>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午9:07:00
	 */
	public abstract SingleResult<DictCategory> selectDictCategoryByPrimaryKey(String DictCategoryId) throws Exception;
	/**
	 * 
	    * @Title:deleteDictCategoryByDictCategoryId 
	    * @Description:  TODO(删除字典类型) 
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午9:07:24
	 */
	public abstract MessageResponse deleteDictCategoryByDictCategoryId(String id,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:findDictCategoryListAll 
	    * @Description:  TODO(获取所有字典类型列表) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<DictCategory>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午9:07:39
	 */
	public abstract List<DictCategory> findDictCategoryListAll(UserProp userProp) throws Exception;

}
