package com.huacainfo.ace.portal.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.vo.UsersVo;

public interface UsersService {
	/**
	 * 
	    * @Title:findUsersSearchList 
	    * @Description:  TODO(系统拥有搜索) 
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<Map<String,String>>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:29:18
	 */
	public abstract PageResult<Map<String, String>> findUsersSearchList(Users condition, int start, int limit, String orderBy) throws Exception;
	/**
	 * 
	    * @Title:findUsersList 
	    * @Description:  TODO(用户分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<UsersVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:29:32
	 */
	public abstract PageResult<UsersVo> findUsersList(Users condition, int start, int limit, String orderBy) throws Exception;
	/**
	 * 
	    * @Title:insertUsers 
	    * @Description:  TODO(添加用户) 
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:29:50
	 */
	public abstract MessageResponse insertUsers(Users obj,UserProp userProp,String flag) throws Exception;
	/**
	 * 
	    * @Title:updateUsers 
	    * @Description:  TODO(更新用户) 
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:30:04
	 * @param flag 
	 */
	public abstract MessageResponse updateUsers(Users obj,UserProp userProp, String flag) throws Exception;
	/**
	 * 
	    * @Title:updateUsersStautsByPrimaryKey 
	    * @Description:  TODO(更新用户状态) 
	 		* @param:        @param usersId
	 		* @param:        @param struts
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:30:18
	 */
	public abstract MessageResponse updateUsersStautsByPrimaryKey(String usersId,String struts,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:selectUsersByPrimaryKey 
	    * @Description:  TODO(获取用户信息) 
	 		* @param:        @param usersId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<UsersVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:30:32
	 */
	public abstract SingleResult<UsersVo> selectUsersByPrimaryKey(String usersId) throws Exception;
	/**
	 * 
	    * @Title:updateUsersForInitPassword 
	    * @Description:  TODO(初始化用户密码) 
	 		* @param:        @param usersId
	 		* @param:        @param password
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:31:31
	 */
	public abstract MessageResponse updateUsersForInitPassword(String usersId,String password,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:insertUsersRole 
	    * @Description:  TODO(用户分配角色) 
	 		* @param:        @param userId
	 		* @param:        @param roleId
	 		* @param:        @param userProp
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:31:50
	 */
	public abstract MessageResponse insertUsersRole(String userId,String[] roleId,UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:selectRoleList 
	    * @Description:  TODO(获取系统所有角色列表) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<Role>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:32:15
	 */
	public abstract PageResult<Role> selectRoleList(UserProp userProp) throws Exception;
	/**
	 * 
	    * @Title:selectRoleListByUserId 
	    * @Description:  TODO(获取用户已分配角色列表) 
	 		* @param:        @param userId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<Role>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:32:34
	 */
	public abstract PageResult<Role> selectRoleListByUserId(String userId) throws Exception;
	
	/**
	 * 删除联系人信息
	 * 
	 * @param id
	 * @param userProp
	 * @return MessageResponse
	 * @version: 2017年02月28日 下午16:47
	 */
	public abstract MessageResponse deleteUsers(String id, UserProp userProp);
	

	/**
	 * 
	    * @Title:updateUsersById
	    * @Description:  TODO(修改企业联系人信息) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws 
	    * @version: 2017年02月28日 下午2:43:34
	 */
	public abstract MessageResponse updateUsersById(Users obj, UserProp curUserProp);
	
	/**
	 * 根据企业编号查询所有联系人
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String,String>>
	 */
	public abstract PageResult<Map<String, String>> findDeIdByUsersList(Users condition, int start, int limit,
			String orderBy);
	
	/**
	 * 更新联系人的状态
	 * 
	 * @param id
	 * @param curUserProp
	 * @return
	 */
	public abstract MessageResponse deleteConUsers(String id, UserProp curUserProp) throws Exception;

	/**
	 * @Description:员工数据导入
	 * @param list
	 * @param userProp
	 * @return
	 * @throws Exception
	 */
	public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;

	public abstract MessageResponse insertReg(Users obj) throws Exception;

}
