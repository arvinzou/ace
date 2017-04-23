package com.huacainfo.ace.portal.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.security.spring.SecurityLoadResouceDefine;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.vo.ResourcesVo;

public interface ResourcesService extends SecurityLoadResouceDefine {
	/**
	 * 
	    * @Title:findResourcesList 
	    * @Description:  TODO(资源管理分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<ResourcesVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:38:58
	 */
	public abstract PageResult<ResourcesVo> findResourcesList(
			Resources condition, int start, int limit, String orderBy)
			throws Exception;
	/**
	 * 
	    * @Title:insertResources 
	    * @Description:  TODO(添加资源) 
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:39:12
	 */
	public abstract MessageResponse insertResources(Resources obj,
			UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:updateResources 
	    * @Description:  TODO(更新资源) 
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:39:27
	 */
	public abstract MessageResponse updateResources(Resources obj,
			UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:selectResourcesByPrimaryKey 
	    * @Description:  TODO(根据主键获取资源信息) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<Resources>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:39:43
	 */
	public abstract SingleResult<Resources> selectResourcesByPrimaryKey(
			String id) throws Exception;
	/**
	 * 
	    * @Title:deleteResourcesByResourcesId 
	    * @Description:  TODO(根据主键删除资源) 
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:41:39
	 */
	public abstract MessageResponse deleteResourcesByResourcesId(String id,
			UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:selectResourcesTreeList 
	    * @Description:  TODO(获取资源树) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<Tree>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:42:18
	 */
	public abstract List<Tree> selectResourcesTreeList() throws Exception;
	/**
	 * 鉴权加载系统的资源
	 */
	public List<Map<String, String>> loadResourceDefine();
	/**
	 * 
	    * @Title:importXls 
	    * @Description:  TODO(Excel导入资源数据) 
	 		* @param:        @param list
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 上午11:42:49
	 */
	public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp)
			throws Exception;


	public MessageResponse updateSequence(List<Map<String, Object>> list, UserProp userProp)
			throws Exception;
}
