package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.vo.UsersVo;

public interface UsersDao {
	/**
	 * @description: init
	 * @param:
	 * @return:
	 * @author: 陈晓克
	 * @version:3.5.1
	 * @exception:
	 * @createDate: 2012-12-14
	 */
	public abstract int init();

	public abstract List<Map<String, String>> findUsersSearchList(
			@Param("condition") Users condition, @Param("start") int start,
			@Param("limit") int limit, @Param("orderBy") String orderBy);

	public abstract int findUsersSearchCount(@Param("condition") Users condition);

	public abstract int insertUsers(@Param("users") Users users);

	public abstract int updateUsersByPrimaryKey(@Param("users") Users users);

	public abstract int updateUsersStautsByPrimaryKey(
			@Param("userId") String usersId, @Param("struts") String struts);

	public abstract List<UsersVo> findUsersList(
			@Param("condition") Users condition, @Param("start") int start,
			@Param("limit") int limit, @Param("orderBy") String orderBy);

	public abstract int findUsersCount(@Param("condition") Users condition);

	public abstract UsersVo selectUsersVoByPrimaryKey(
			@Param("userId") String userId);

	public abstract int updateUsersForInitPassword(
			@Param("userId") String userId, @Param("password") String password);

	public abstract int isExitUsersAccount(@Param("account") String account);

	public abstract int insertUsersRole(@Param("userId") String userId,
			@Param("roleId") String[] roleId);

	public abstract List<Role> selectRoleList(@Param("syid") String syid);

	public abstract List<Role> selectRoleListByUserId(
			@Param("userId") String userId);

	public abstract int isExitUsersByEmail(@Param("email") String email);

	/**
	 * 删除联系人信息
	 * 
	 * @param id
	 * @version: 2017年02月28日 下午16:47
	 */
	public abstract void deleteUsersById(@Param("userId") String id);

	/**
	 * 根据企业编号查询所有用户
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return List<Map<String, String>>
	 */
	public abstract List<Map<String, String>> findDeIdByUsersList(
			@Param("condition") Users condition, @Param("start") int start,
			@Param("limit") int limit, @Param("orderBy") String orderBy);

	/**
	 * 根据企业编号查询所有用户的总数
	 * 
	 * @param condition
	 * @return int
	 */
	public abstract int findDeIdByUsersCount(@Param("condition") Users condition);

	/**
	 * 更新联系人的状态
	 * 
	 * @param id
	 */
	public abstract void updateUsersIdByStatus(@Param("userId") String id);

	public int updateCurSyid(@Param("syid") String syid,
			@Param("userId") String userId);

	public int updateUserOpenId(@Param("userId") String userId,@Param("openId") String openId);


	public int updateUserAppOpenId(@Param("userId") String userId,@Param("appOpenId") String appOpenId);



	List<Map<String,Object>> selectWxUser(@Param("condition") Map<String,Object> condition);

	List<Map<String,Object>> selectAppWxUser(@Param("condition") Map<String,Object> condition);

	List<Map<String,Object>> selectAllAppWxUserList(UserProp userProp);

	List<Map<String,Object>> selectAllWxUserList(UserProp userProp);



}
