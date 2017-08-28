package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.CarProduction;
import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.CarInfo;
import com.huacainfo.ace.gesp.model.CarType;
import com.huacainfo.ace.gesp.model.DriverInfo;

public interface IndustryResourceDao {
	List<Map<String,Object>> findListDept(
			@Param("condition") Map<String,Object> condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCountDept(@Param("condition") Map<String,Object> condition);
	
	
	List<Map<String,Object>> findListCar(
			@Param("condition") Map<String,Object> condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCountCar(@Param("condition") Map<String,Object> condition);
	
	
	List<Map<String,Object>> findListDriver(
			@Param("condition") Map<String,Object> condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCountDriver(@Param("condition") Map<String,Object> condition);
	
	CarInfo selectCarInfoById(String id);
	CarType selectCarTypeInfoById(String id);
	CarProduction selectCarProductionInfoById(String id);
	DriverInfo selectDriverInfoById(String id);

	/**
	 * 修改车辆信息
	 * 
	 * @param obj
	 */
	void updateCarTypeInfoById(CarInfo obj);

	/**
	 * 修改车辆出厂信息
	 * 
	 * @param obj
	 */
	void updateCarProductionInfoById(CarProduction obj);

	/**
	 * 修改司机信息
	 * 
	 * @param obj
	 */
	void updateDriverInfoById(DriverInfo obj);

	/**
	 * 查询企业的所有车辆信息
	 * 
	 * @param condition
	 * @param start
	 * @param i
	 * @param orderBy
	 * @return List<Map<String, Object>> 
	 */
	List<Map<String, Object>> findOwnerCarList(
			@Param("condition") Map<String,Object> condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	/**
	 * 查询企业拥有车辆的总数
	 * 
	 * @param condition
	 * @return int
	 */
	int findOwnerCarCount(@Param("condition") Map<String, Object> condition);

	/**
	 * 查询企业的所有司机信息
	 * 
	 * @param condition
	 * @param start
	 * @param i
	 * @param orderBy
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> findOwnerDriverList(
			@Param("condition") Map<String,Object> condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	/**
	 * 查询企业拥有司机的总数
	 * 
	 * @param condition
	 * @return
	 */
	int findOwnerDriverCount(@Param("condition") Map<String, Object> condition);
	
	
	List<Map<String, Object>> findCar_BaseByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_plateNoChangeByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_OwnerChangeByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_otherChangeByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_MainPartsChangeByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_useRecordByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_trafficAccidentByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_TechParamByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_RepairByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_GradeEvaluationByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_illegalByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);
	List<Map<String, Object>> findCar_OwerDriverByNumberColor(@Param("plateNo") String plateNo,@Param("color")String color);

	/**
	 * 查询所有企业
	 * 
	 * 引用(企业联系人添加修改)
	 * @param corpId 协会编号
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> findAllDeptList(@Param("pdeptId") String corpId) throws Exception;
 
	
}
