package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.Qualifications;
import com.huacainfo.ace.gesp.vo.QualificationsQVo;

public interface QualificationsDao {
	int deleteByPrimaryKey(String QualificationsId);

	int insert(Qualifications record);

	Qualifications selectByPrimaryKey(String QualificationsId);

	int updateByPrimaryKey(Qualifications record);

	List<Map<String,Object>> findList(
			@Param("condition") QualificationsQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") QualificationsQVo condition);

	int isExit(Qualifications record);

	List<Map<String, Object>> selectQualificationsList(@Param("deptId") String deptId, @Param("parentDeptId")String parentDeptId, @Param("status")String status);

	int updateAudit(Qualifications record);

	Qualifications selectByDeptIdAndCategory(@Param("deptId") String deptId,
			@Param("category") String category);

	Map<String, Object> selectCountByDeptID(@Param("deptId") String corpId, @Param("parentDeptId")String parentDeptId);

	/**
	 * 
	 * @param corpId
	 * @param string
	 * @return
	 */
	int updateBystatus(@Param("deptId")String corpId, @Param("status")String status);

	int updateQualiByFileSrc(Qualifications q);

	
}