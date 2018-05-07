package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.vo.DepartmentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DepartmentDao {
	/**
	 * @description: init
	 * @param:
	 * @return:
	 * @author: 陈晓克
	 * @version:3.5.1
	 * @exception:
	 * @createDate: 2012-12-14
	 */

	public int isExitByUserId(String userId);

	public int insertDepartment(Department department);

	public int updateDepartmentByPrimaryKey(Department department);

	public int updateDepartmentStautsByPrimaryKey(
			@Param("departmentId") String departmentId,
			@Param("struts") String struts);

	public List<DepartmentVo> findDepartmentList(
			@Param("condition") Department condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	public int findDepartmentCount(@Param("condition") Department condition);

	public DepartmentVo selectDepartmentVoByPrimaryKey(
			@Param("departmentId") String departmentId);

	public List<Map<String, Object>> selectDepartmentTreeList(@Param("pid")String pid,@Param("syid")String syid);

	public int delDepartmentByPrimaryKey(
			@Param("departmentId") String departmentId);

	public List<Map<String, String>> selectUsersListByDepartmentId(
			@Param("departmentId") String departmentId);

	int isExit(Department record);

	int isExitEmail(String email);

	int isExitBussLicenseNo(Department record);

	int insertUsersAndRole(Users users);

	int updateActivateBySeat(String seat);

    int insertDepartmentWithDepId(Department department);

}
