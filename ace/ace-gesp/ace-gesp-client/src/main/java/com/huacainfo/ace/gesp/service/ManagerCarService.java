package com.huacainfo.ace.gesp.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;

public interface ManagerCarService {

	/**
	 * 查询车辆的基本信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	PageResult<Map<String, Object>> findCarInfoList(Map<String, Object> condition, int start, int limit,
			String orderBy) throws Exception;

	/**
	 * 根据条件查询车辆的违规违章信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	PageResult<Map<String, Object>> findCarIllegalList(Map<String, Object> condition, int start, int limit,
			String orderBy) throws Exception;

	/**
	 * 根据条件查询车辆的综合性能检测信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	PageResult<Map<String, Object>> findCarPerTestList(Map<String, Object> condition, int start, int limit,
			String orderBy) throws Exception;

	/**
	 * 根据条件查询车辆的二级维护信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	PageResult<Map<String, Object>> findCarTwoLeMainList(Map<String, Object> condition, int start, int limit,
			String orderBy);

	/**
	 * 根据编号查询车辆的基本信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	SingleResult<Map<String, Object>> selectByCarInfo(String id);

	/**
	 * 根据车牌号码和颜色查询车辆的综合性能检测信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	List<Map<String, Object>> selectByCarPerTest(String plateNo, String color);

	/**
	 * 根据车牌号码和颜色查询车辆的二级维护信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	List<Map<String, Object>> selectByCarTwoLeMain(String plateNo, String color);

	/**
	 * 根据车牌号码和颜色查询车辆的违规违章信息
	 * 
	 * @param plateNo
	 * @param color
	 * @return
	 */
	List<Map<String, Object>> selectByCarIllegal(String plateNo, String color);




}
