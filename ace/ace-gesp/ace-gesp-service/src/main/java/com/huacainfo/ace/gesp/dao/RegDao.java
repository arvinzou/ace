
package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.vo.DepartmentVo;
import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.Users;

public interface RegDao {

	int deleteByPrimaryKey(String departmentId);

	int insert(Department record);

	DepartmentVo selectByPrimaryKey(@Param("departmentId") String departmentId,
			@Param("parentDepartmentId") String parentDepartmentId);

	int updateByPrimaryKeySelective(Department record);
	int updateByPrimaryKey(Department record);

	int isExit(Department record);
	int isExitEmail(String email);
	int isExitBussLicenseNo(Department record);

	int insertUsersAndRole(Users users);

	int updateActivateBySeat(String seat);

	Department selectDepartInfoByPrimaryKey(@Param("departmentId") String corpId);

	Department selectDepartNameById(@Param("departmentName") String departmentName);

	int insertUsers(Users users);

	int insertTransBuss(Department de);

	int insertDepartmentSociety(Department de);

	Map<String, Object> selectDicby(@Param("categoryId") String pid, @Param("code") String id);

	Map<String, Object> selectInfoByDepartName(@Param("departmentName") String departmentName);

	List<Map<String, String>> loadResourceDefine();

	List<Map<String, String>> findUsersSearchList(@Param("condition") Users condition, @Param("start") int start,
			@Param("limit") int limit, @Param("orderBy") String orderBy);

	int insertUsersRole(@Param("userId") String userId, @Param("roleId") String[] roleId);

	int isExitUsersAccount(@Param("account") String account);

	/**
	 * 根据名称删除
	 * 
	 * @param name
	 * @return
	 */
	int deleteByDepartment(@Param("fullName") String name);

	/**
	 * 新增
	 * 
	 * @param companyInfo
	 * @return
	 */
	int insertCompanyInfo(Map<String, Object> companyInfo);

}