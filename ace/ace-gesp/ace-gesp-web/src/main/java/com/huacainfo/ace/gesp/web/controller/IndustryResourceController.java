package com.huacainfo.ace.gesp.web.controller;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.CarInfo;
import com.huacainfo.ace.gesp.model.CarProduction;
import com.huacainfo.ace.gesp.model.DriverInfo;
import com.huacainfo.ace.gesp.service.IndustryResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.model.CarType;

@Controller
@RequestMapping("/industryResource")
public class IndustryResourceController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IndustryResourceService industryResourceService;

	@RequestMapping(value = "/findDeptList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findDeptList(
			 PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if(CommonUtils.isBlank(condition.get("deptId"))){
			condition.put("deptId",this.getCurUserProp().getCorpId());
		}
		String code = condition.get("deptId").toString();
		if(code.length()<5){
			condition.put("deptId","5");
		}
		PageResult<Map<String,Object>> rst = this.industryResourceService
				.findDeptList(condition, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	
	@RequestMapping(value = "/findOwnerCarList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findOwnerCarList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.industryResourceService
				.findOwnerCarList(condition, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	
	
	@RequestMapping(value = "/findCarList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findCarList( PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if(CommonUtils.isBlank(condition.get("deptId"))){
			condition.put("deptId",this.getCurUserProp().getCorpId());
		}
		String code = condition.get("deptId").toString();
		if(code.length()<5){
			condition.put("deptId","5");
		}
		PageResult<Map<String,Object>> rst = this.industryResourceService
				.findCarList(condition, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	
	
	@RequestMapping(value = "/findOwnerDriverList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findOwnerDriverList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.industryResourceService
				.findOwnerDriverList(condition, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	
	
	
	@RequestMapping(value = "/findDriverList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findDriverList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if(CommonUtils.isBlank(condition.get("deptId"))){
			condition.put("deptId",this.getCurUserProp().getCorpId());
		}
		String code = condition.get("deptId").toString();
		if(code.length()<5){
			condition.put("deptId","5");
		}
		PageResult<Map<String,Object>> rst = this.industryResourceService
				.findDriverList(condition, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	@RequestMapping(value = "/selectCarInfoById.do")
	@ResponseBody
	public SingleResult<CarInfo> selectCarInfoById(String id)
			throws Exception {
		return this.industryResourceService.selectCarInfoById(id);
	}
	@RequestMapping(value = "/selectCarTypeInfoById.do")
	@ResponseBody
	public SingleResult<CarType> selectCarTypeInfoById(String id)
			throws Exception {
		return this.industryResourceService.selectCarTypeInfoById(id);
	}
	@RequestMapping(value = "/selectCarProductionInfoById.do")
	@ResponseBody
	public SingleResult<CarProduction> selectCarProductionInfoById(
			String id) throws Exception {
		return this.industryResourceService.selectCarProductionInfoById(id);
	}
	@RequestMapping(value = "/selectDriverInfoById.do")
	@ResponseBody
	public SingleResult<DriverInfo> selectDriverInfoById(String id)
			throws Exception {
		return this.industryResourceService.selectDriverInfoById(id);
	}

	/**
	 * 修改车辆信息
	 * 
	 * @param jsons
	 * @return MessageResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCarTypeInfoById.do")
	@ResponseBody
	public MessageResponse updateCarTypeInfoById(String jsons) throws Exception {
		CarInfo obj = JSON.parseObject(jsons, CarInfo.class);
		obj.setModifyDate(new java.util.Date());
		obj.setModifyUserId(getCurUserProp().getUserId());
		obj.setModifyUserName(getCurUserProp().getName());
		return industryResourceService.updateCarTypeInfoById(obj,getCurUserProp());
	}
	

	/**
	 * 修改车辆出厂信息
	 * 
	 * @param jsons
	 * @return MessageResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCarProductionInfoById.do")
	@ResponseBody
	public MessageResponse updateCarProductionInfoById(String jsons) throws Exception {
		CarProduction obj = JSON.parseObject(jsons, CarProduction.class);
		obj.setModifyDate(new java.util.Date());
		obj.setModifyUserId(getCurUserProp().getUserId());
		obj.setModifyUserName(getCurUserProp().getName());
		return industryResourceService.updateCarProductionInfoById(obj,getCurUserProp());
	}

	/**
	 * 修改司机信息
	 * 
	 * @param jsons
	 * @return MessageResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateDriverInfoById.do")
	@ResponseBody
	public MessageResponse updateDriverInfoById(String jsons) throws Exception {
		DriverInfo obj = JSON.parseObject(jsons, DriverInfo.class);
		obj.setLastModifyDate(new java.util.Date());
		obj.setLastModifyUserId(getCurUserProp().getUserId());
		obj.setLastModifyUserName(getCurUserProp().getName());
		return industryResourceService.updateDriverInfoById(obj,getCurUserProp());
	}
	
	/**
	 * 查询所有企业
	 * 
	 * 引用(企业联系人添加修改)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllDeptList.do")
	@ResponseBody
	public List<Map<String,Object>> findAllDeptList() throws Exception {
		List<Map<String,Object>> rst = this.industryResourceService.findAllDeptList(this.getCurUserProp().getCorpId());
		return rst;
	}
	
	@RequestMapping(value = "/findCar_BaseByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_BaseByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_BaseByNumberColor(plateNo,color);
	}
	@RequestMapping(value = "/findCar_plateNoChangeByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_plateNoChangeByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_plateNoChangeByNumberColor(plateNo,color);
	}
	
	@RequestMapping(value = "/findCar_OwnerChangeByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_OwnerChangeByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_OwnerChangeByNumberColor(plateNo,color);
	}
	@RequestMapping(value = "/findCar_otherChangeByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_otherChangeByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_otherChangeByNumberColor(plateNo,color);
	}
	@RequestMapping(value = "/findCar_MainPartsChangeByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_MainPartsChangeByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_MainPartsChangeByNumberColor(plateNo,color);
	}
	@RequestMapping(value = "/findCar_useRecordByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_useRecordByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_useRecordByNumberColor(plateNo,color);
	}
	@RequestMapping(value = "/findCar_trafficAccidentByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_trafficAccidentByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_trafficAccidentByNumberColor(plateNo,color);
	}
	@RequestMapping(value = "/findCar_TechParamByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_TechParamByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_TechParamByNumberColor(plateNo,color);
	}
	@RequestMapping(value = "/findCar_RepairByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_RepairByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_RepairByNumberColor(plateNo,color);
	}
	@RequestMapping(value = "/findCar_GradeEvaluationByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_GradeEvaluationByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_GradeEvaluationByNumberColor(plateNo,color);
	}
	
	@RequestMapping(value = "/findCar_illegalByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_illegalByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_illegalByNumberColor(plateNo,color);
	}
	@RequestMapping(value = "/findCar_OwerDriverByNumberColor.do")
	@ResponseBody
	public   ListResult<Map<String, Object>> findCar_OwerDriverByNumberColor(  String plateNo,String color) 
	{
		return industryResourceService.findCar_OwerDriverByNumberColor(plateNo,color);
	}
}
