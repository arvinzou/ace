package com.huacainfo.ace.gesp.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.CarProduction;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.CarInfo;
import com.huacainfo.ace.gesp.model.CarType;
import com.huacainfo.ace.gesp.model.DriverInfo;

public interface IndustryResourceService {

	public abstract PageResult<Map<String,Object>> findDeptList(
			Map<String,Object> condition, int start, int limit, String orderBy)
			throws Exception;

	public abstract PageResult<Map<String,Object>> findCarList(
			Map<String,Object> condition, int start, int limit, String orderBy)
			throws Exception;
	
	public abstract PageResult<Map<String,Object>> findDriverList(
			Map<String,Object> condition, int start, int limit, String orderBy)
			throws Exception;
	
	SingleResult<CarInfo> selectCarInfoById(String id)throws Exception;
	SingleResult<CarType> selectCarTypeInfoById(String id)throws Exception;
	SingleResult<CarProduction> selectCarProductionInfoById(String id)throws Exception;
	SingleResult<DriverInfo> selectDriverInfoById(String id)throws Exception;

	/**
	 * 修改车辆信息
	 * 
	 * @param obj
	 * @param userProp 
	 * @return MessageResponse
	 */
	public MessageResponse updateCarTypeInfoById(CarInfo obj, UserProp userProp) throws Exception;

	/**
	 * 修改车辆出厂信息
	 * 
	 * @param jsons
	 * @return MessageResponse
	 */
	public MessageResponse updateCarProductionInfoById(CarProduction obj, UserProp curUserProp) throws Exception;

	/**
	 * 修改司机信息
	 * 
	 * @param obj
	 * @param curUserProp
	 * @return MessageResponse
	 */
	public MessageResponse updateDriverInfoById(DriverInfo obj, UserProp curUserProp) throws Exception;

	/**
	 * 查询企业的所有车辆信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	public abstract PageResult<Map<String, Object>> findOwnerCarList(Map<String, Object> condition, int start,
			int limit, String orderBy)throws Exception;

	/**
	 *  查询企业的所有司机信息
	 *  
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	public abstract PageResult<Map<String, Object>> findOwnerDriverList(Map<String, Object> condition, int start,
			int limit, String orderBy)throws Exception;
	
	public abstract ListResult<Map<String, Object>> findCar_BaseByNumberColor(  String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_plateNoChangeByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_OwnerChangeByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_otherChangeByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_MainPartsChangeByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_useRecordByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_trafficAccidentByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_TechParamByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_RepairByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_GradeEvaluationByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_illegalByNumberColor(String plateNo,String color);
	public abstract ListResult<Map<String, Object>> findCar_OwerDriverByNumberColor(String plateNo,String color);

	/**
	 * 查询所有企业
	 * 
	 * 引用(企业联系人添加修改)
	 * @param corpId 协会编号
	 * @return List<Map<String, Object>>
	 */
	public abstract List<Map<String, Object>> findAllDeptList(String corpId) throws Exception;
}
