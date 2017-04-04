package com.huacainfo.ace.portal.service;

import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.CheckTree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.vo.RoleVo;

public interface RoleService {
	/**
	 * 
	    * @Title:findRoleList 
	    * @Description:  TODO(角色分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<RoleVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:07:21
	 */
	public abstract PageResult<RoleVo> findRoleList(Role condition, int start, int limit, String orderBy) throws Exception;
	/**
	 * 
	    * @Title:insertRole 
	    * @Description:  TODO(添加角色) 
	 		* @param:        @param role
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:08:15
	 */
	public abstract MessageResponse insertRole(Role role,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:updateRole 
	    * @Description:  TODO(更新角色) 
	 		* @param:        @param role
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:08:28
	 */
	public abstract MessageResponse updateRole(Role role,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:insertRoleResources 
	    * @Description:  TODO(添加角色拥有的资源) 
	 		* @param:        @param roleId
	 		* @param:        @param resourcesId
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:08:39
	 */
	public abstract MessageResponse insertRoleResources(String roleId,String[] resourcesId,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:selectRoleByPrimaryKey 
	    * @Description:  TODO(根据主键获取角色信息) 
	 		* @param:        @param roleId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<RoleVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:14:17
	 */
	public abstract SingleResult<RoleVo> selectRoleByPrimaryKey(String roleId) throws Exception;
	/**
	 * 
	    * @Title:selectRoleResourceTreeList 
	    * @Description:  TODO(获取角色拥有的资源树) 
	 		* @param:        @param roleId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<CheckTree>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:14:42
	 */
	public abstract List<CheckTree>  selectRoleResourceTreeList(String roleId) throws Exception;
	/**
	 * 
	    * @Title:deleteRoleByRoleId 
	    * @Description:  TODO(删除角色) 
	 		* @param:        @param roleId
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:15:03
	 */
	public abstract MessageResponse deleteRoleByRoleId(String roleId,UserProp userProp) throws Exception;
	
}
