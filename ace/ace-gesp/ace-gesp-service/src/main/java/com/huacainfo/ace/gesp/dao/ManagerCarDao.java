package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ManagerCarDao {
	

	/**
	 * 根据条件查询车辆的基本信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	List<Map<String,Object>> findCarInfoList(
			@Param("condition") Map<String,Object> condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	/**
	 * 根据条件查询车辆基本信息的总条数
	 * 
	 * @param condition
	 * @return int
	 */
	int findCarInfoCount(@Param("condition") Map<String,Object> condition);

	/**
	 * 根据条件查询车辆的违规违章信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	List<Map<String, Object>> findCarIllegalList(
			@Param("condition") Map<String,Object> condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	/**
	 * 根据条件查询车辆的违规违章信息的总条数
	 * 
	 * @param condition
	 * @return int
	 */
	int findCarIllegalCount(@Param("condition") Map<String,Object> condition);

	/**
	 * 根据条件查询车辆的综合性能检测信息
	 * 
	 * @param condition
	 * @param start
	 * @param i
	 * @param orderBy
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> findCarPerTestList(
			@Param("condition") Map<String,Object> condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	/**
	 * 根据条件查询车辆的综合性能检测信息的总条数
	 * 
	 * @param condition
	 * @return int
	 */
	int findCarPerTestCount(@Param("condition") Map<String,Object> condition);

	/**
	 * 根据条件查询车辆的二级维护信息
	 * 
	 * @param condition
	 * @param start
	 * @param i
	 * @param orderBy
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> findCarTwoLeMainList(
			@Param("condition") Map<String,Object> condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	/**
	 * 根据条件查询车辆的二级维护信息总条数
	 * 
	 * @param condition
	 * @return int
	 */
	int findCarTwoLeMainCount(@Param("condition") Map<String,Object> condition);


	/**
	 * 根据编号查询车辆的基本信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	Map<String, Object> selectByCarInfo(@Param("id")String id);

	/**
	 * 根据车牌号码和颜色查询车辆的综合性能检测信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	List<Map<String, Object>> selectByCarPerTest(@Param("plateNo")String plateNo, @Param("color")String color);

	/**
	 * 根据车牌号码和颜色查询车辆的二级维护信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	List<Map<String, Object>> selectByCarTwoLeMain(@Param("plateNo")String plateNo, @Param("color")String color);

	/**
	 * 根据车牌号码和颜色查询车辆的违规违章信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	List<Map<String, Object>> selectByCarIllegal(@Param("plateNo")String plateNo, @Param("color")String color);
	
}
