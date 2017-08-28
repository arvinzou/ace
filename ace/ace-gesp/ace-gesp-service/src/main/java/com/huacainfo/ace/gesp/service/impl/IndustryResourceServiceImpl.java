package com.huacainfo.ace.gesp.service.impl;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.CarProduction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.dao.IndustryResourceDao;
import com.huacainfo.ace.gesp.model.CarInfo;
import com.huacainfo.ace.gesp.model.CarType;
import com.huacainfo.ace.gesp.model.DriverInfo;
import com.huacainfo.ace.gesp.service.IndustryResourceService;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("industryResourceService")
public class IndustryResourceServiceImpl implements IndustryResourceService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IndustryResourceDao industryResourceDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<Map<String, Object>> findDeptList(
			Map<String, Object> condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.industryResourceDao.findListDept(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.industryResourceDao.findCountDept(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public PageResult<Map<String, Object>> findCarList(
			Map<String, Object> condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.industryResourceDao.findListCar(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.industryResourceDao.findCountCar(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public PageResult<Map<String, Object>> findOwnerCarList(Map<String, Object> condition, int start, int limit,
			String orderBy)throws Exception{
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.industryResourceDao.findOwnerCarList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.industryResourceDao.findOwnerCarCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
			
	/**
	 * 查询企业的所有司机信息
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String, Object>>
	 */
	public PageResult<Map<String, Object>> findOwnerDriverList(Map<String, Object> condition, int start, int limit,
			String orderBy)throws Exception {
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.industryResourceDao.findOwnerDriverList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.industryResourceDao.findOwnerDriverCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
	public PageResult<Map<String, Object>> findDriverList(
			Map<String, Object> condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<Map<String, Object>> rst = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = this.industryResourceDao
				.findListDriver(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.industryResourceDao.findCountDriver(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public SingleResult<CarInfo> selectCarInfoById(String id)
			throws Exception {
		SingleResult<CarInfo> rst = new SingleResult<CarInfo>();
		rst.setValue(this.industryResourceDao.selectCarInfoById(id));
		return rst;
	}

	public SingleResult<CarType> selectCarTypeInfoById(String id)
			throws Exception {
		SingleResult<CarType> rst = new SingleResult<CarType>();
		rst.setValue(this.industryResourceDao.selectCarTypeInfoById(id));
		return rst;
	}

	public SingleResult<CarProduction> selectCarProductionInfoById(
			String id) throws Exception {
		SingleResult<CarProduction> rst = new SingleResult<CarProduction>();
		rst.setValue(this.industryResourceDao.selectCarProductionInfoById(id));
		return rst;
	}

	public SingleResult<DriverInfo> selectDriverInfoById(String id)
			throws Exception {
		SingleResult<DriverInfo> rst = new SingleResult<DriverInfo>();
		rst.setValue(this.industryResourceDao.selectDriverInfoById(id));
		return rst;
	}

	/**
	 * 修改车辆信息
	 * 
	 * @param obj
	 * @param userProp
	 * @return MessageResponse
	 */
	public MessageResponse updateCarTypeInfoById(CarInfo obj, UserProp userProp)
			throws Exception {
		this.industryResourceDao.updateCarTypeInfoById(obj);
		
		this.dataBaseLogService.log("车辆基本信息修改", "车辆基本信息修改", "车牌号："+obj.getPlateNumber()+",车牌颜色:"+obj.getPlateColor(), 
				"车牌号："+obj.getPlateNumber()+",车牌颜色:"+obj.getPlateColor(),
				obj.getPlateNumber(), userProp);
		return new MessageResponse(0, "车辆信息修改成功！");
	}

	/**
	 * 修改车辆出厂信息
	 * 
	 * @param obj
	 * @param curUserProp
	 * @return MessageResponse
	 */
	public MessageResponse updateCarProductionInfoById(CarProduction obj, UserProp curUserProp)
			throws Exception {
		this.industryResourceDao.updateCarProductionInfoById(obj);
		
		this.dataBaseLogService.log("车辆出厂信息修改", "车辆出厂信息修改", "Vin码："+obj.getVin()+",车牌编号:"+obj.getId(), "Vin码："+obj.getVin()+",车牌编号:"+obj.getId(),
				obj.getId(), curUserProp);
		return new MessageResponse(0, "车辆信息修改成功！");
	}

	/**
	 * 修改司机信息
	 * 
	 * @param obj
	 * @param curUserProp
	 * @return MessageResponse
	 */
	public MessageResponse updateDriverInfoById(DriverInfo obj, UserProp curUserProp) 
			 throws Exception{
		this.industryResourceDao.updateDriverInfoById(obj);
		DriverInfo d = this.industryResourceDao.selectDriverInfoById(obj.getId());
		this.dataBaseLogService.log("司机信息修改", "司机信息修改", "姓名"+d.getName()+",身份证号："+d.getIdCard()+",从业资格证号:"+d.getCertNumber()+",驾驶证号:"+d.getDriverLicNo()+",编号:"+obj.getId(), 
				 "姓名"+obj.getName()+",身份证号："+obj.getIdCard()+",从业资格证号:"+obj.getCertNumber()+",驾驶证号:"+obj.getDriverLicNo()+",编号:"+obj.getId(),
				obj.getName(), curUserProp);
		return new MessageResponse(0, "司机信息修改成功！");
	}

	@Override
	public ListResult<Map<String, Object>> findCar_BaseByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_BaseByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_plateNoChangeByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_plateNoChangeByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_OwnerChangeByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_OwnerChangeByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_otherChangeByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_otherChangeByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_MainPartsChangeByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_MainPartsChangeByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_useRecordByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_useRecordByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_trafficAccidentByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_trafficAccidentByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_TechParamByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_TechParamByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_RepairByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_RepairByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_GradeEvaluationByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_GradeEvaluationByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_illegalByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_illegalByNumberColor(plateNo,color));
		return r;
	}

	@Override
	public ListResult<Map<String, Object>> findCar_OwerDriverByNumberColor(String plateNo, String color) {
		ListResult<Map<String, Object>> r=new ListResult<>(0, "");
		r.setValue(industryResourceDao.findCar_OwerDriverByNumberColor(plateNo,color));
		return r;
	}


	/**
	 * 查询所有企业
	 * 
	 * 引用(企业联系人添加修改)
	 * @param corpId 协会编号
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> findAllDeptList(String pdeptId) throws Exception {
		return this.industryResourceDao.findAllDeptList(pdeptId);
	}

}
