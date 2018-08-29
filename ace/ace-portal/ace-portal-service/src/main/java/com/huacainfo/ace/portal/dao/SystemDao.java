package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.Files;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.model.Users;

/**
 * @author chenxiaoke
 *
 */
/**
 * @author chenxiaoke
 *
 */
public interface SystemDao {
	/**
	 * /**
	 * 
	 * @description: 根据个人编号获取用户基本信息
	 * @param: userId 个人编号
	 * @return: Users
	 * @author: 陈晓克
	 * @version:3.5.1
	 * @exception:
	 * @createDate: 2012-12-15
	 */
	public abstract Users selectUsersByUserId(@Param("userId") String userId);

	/**
	 * @description: 根据部门编号获取用户部门基本信息
	 * @param: departmentId 部门编号
	 * @return: Department
	 * @author: 陈晓克
	 * @version:3.5.1
	 * @exception:
	 * @createDate: 2012-12-15
	 */
	public abstract Department selectDepartmentByDepartmentId(
			@Param("departmentId") String departmentId);

	/**
	 * @description: 根据个人编号获取用户资源信息
	 * @param: userId 个人编号
	 * @return: List<Resources>
	 * @author: 陈晓克
	 * @version:3.5.1
	 * @exception:
	 * @createDate: 2012-12-15
	 */
	public abstract List<Resources> selectResourcesByUserId(
			@Param("userId") String userId, @Param("portal") String portal);

	public abstract List<Map<String, String>> selectProvinceTreeList();

	public abstract List<Map<String, String>> selectProvinceTreeListByPid(
			@Param("pid") String pid);

	public abstract int insertFiles(@Param("files") Files files);

	public abstract int updatePassword(@Param("userId") String userId,
			@Param("password") String password);

	/**
	 * @description: 根据账户名获取用户基本信息
	 * @param: account 账户名
	 * @return: Users
	 * @author: 陈晓克
	 * @version:3.5.1
	 * @exception:
	 * @createDate: 2012-12-15
	 */
	public abstract Users selectUsersByAccount(@Param("account") String account);


	public abstract Users  selectUsersByOpenId(@Param("openId") String openId);

	public abstract List<Map<String, String>> selectRoleListByUserId(
			@Param("userId") String userId);

	public abstract List<Map<String, String>> selectRoleTypeListByUserId(
			@Param("userId") String userId);

	public abstract List<Map<String, String>> selectDepartment(
			@Param("params") Map<String, String> params,@Param("syid") String syid);

	public abstract List<Map<String, String>> selectUsers(
			@Param("params") Map<String, Object> params,@Param("syid") String syid);

	/**
	 * @description: 根据Email获取用户基本信息
	 * @param: email email
	 * @return: Users
	 * @author: 陈晓克
	 * @version:3.5.1
	 * @exception:
	 * @createDate: 2014-10-15
	 */
	public abstract Users selectUsersByEmail(@Param("email") String email);

	public abstract List<Map<String, Object>> selectDepartmentTreeList(
			@Param("pid") String pid,@Param("syid") String syid);

	public List<Config> loadConfig(String syid);

	public List<Map<String, String>> selectSyidByUserId(String userId);

	/**
	 * 修改邮箱
	 * 
	 * @param email
	 * @param userProp
	 * @return MessageResponse
	 */
	public abstract void updateEmail(@Param("email") String email,
			@Param("userId") String userId);

	int updateUserLocked(@Param("locked")String locked,@Param("account")String account);

}
