package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.vo.RoleVo;

public interface RoleDao {
	/**
	 *@description: init
	 *@param:  
	 *@return:
	 *@author: 陈晓克
	 *@version:3.5.1
	 *@exception:
	 *@createDate: 2012-12-14
	 */
	public abstract int init();
	
	public abstract int insertRole(@Param("role")Role role);
	
	public abstract int updateRoleByPrimaryKey(@Param("role")Role role);
	
	public abstract int isExitRoleByRoleName(@Param("roleName")String roleName);
	
	public abstract List<RoleVo> findRoleList (@Param("condition")Role condition,@Param("start")int start,@Param("limit") int limit,@Param("orderBy")String orderBy);
	
	public abstract int findRoleCount (@Param("condition")Role condition);
	
	public abstract RoleVo selectRoleVoByPrimaryKey(@Param("roleId")String roleId);
	
	public abstract int deleteRoleByRoleId(@Param("roleId")String roleId);
	
	public abstract List<Map<String,Object>> selectRoleResourceTreeList(@Param("roleId")String roleId);
	public abstract int deleteRoleResources(@Param("roleId")String roleId);
	
	public abstract int insertRoleResources(@Param("roleId")String roleId,@Param("resourcesId")String resourcesId);
	
	public abstract int isExitRoleUsedByRoleId(@Param("roleId")String roleId);
}
