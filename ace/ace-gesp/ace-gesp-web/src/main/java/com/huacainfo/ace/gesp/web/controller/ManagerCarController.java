package com.huacainfo.ace.gesp.web.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.service.ManagerCarService;

@Controller
@RequestMapping("/managerCar")
public class ManagerCarController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ManagerCarService managerCarService;

	/**
	 * 根据条件查询车辆的基本信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCarInfoList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findCarInfoList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.managerCarService.findCarInfoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	
	/**
	 * 根据条件查询车辆的基本信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/findOwnerCarList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findOwnerCarList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",  this.getCurUserProp().getParentCorpId());
			condition.put("pdeptId", this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.managerCarService.findCarInfoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	/**
	 * 根据条件查询车辆的违规违章信息（协会）
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCarIllegalList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findCarIllegalList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.managerCarService.findCarIllegalList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	/**
	 * 根据条件查询车辆的违规违章信息(企业)
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/findOwnerCarIllegalList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findOwnerCarIllegalList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",this.getCurUserProp().getParentCorpId());
			condition.put("pdeptId", this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.managerCarService.findCarIllegalList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	/**
	 * 根据条件查询车辆的二级维护信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCarTwoLeMainList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findCarTwoLeMainList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.managerCarService.findCarTwoLeMainList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	/**
	 * 根据条件查询车辆的二级维护信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCarOwnerTwoLeMainList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findCarOwnerTwoLeMainList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",this.getCurUserProp().getParentCorpId());
			condition.put("pdeptId", this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.managerCarService.findCarTwoLeMainList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	/**
	 * 根据条件查询车辆的综合性能检测信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCarOwnerPerTestList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findCarOwnerPerTestList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",this.getCurUserProp().getParentCorpId());
			condition.put("pdeptId", this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.managerCarService.findCarPerTestList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	/**
	 * 根据条件查询车辆的综合性能检测信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCarPerTestList.do")
	@ResponseBody
	public PageResult<Map<String,Object>> findCarPerTestList(PageParamNoChangeSord page)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(condition.get("deptId"))) {
			condition.put("deptId",this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String,Object>> rst = this.managerCarService.findCarPerTestList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	/**
	 * 根据车牌号码和颜色查询车辆的基本信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByCarInfo.do")
	@ResponseBody
	public SingleResult<Map<String, Object>> selectByCarInfo(String id)
			throws Exception {
		return this.managerCarService.selectByCarInfo(id);
	}

	/**
	 * 根据车牌号码和颜色查询车辆的综合性能检测信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByCarPerTest.do")
	@ResponseBody
	public List<Map<String, Object>> selectByCarPerTest(String plateNo,String color)
			throws Exception {
		return this.managerCarService.selectByCarPerTest(plateNo, color);
	}

	/**
	 * 根据车牌号码和颜色查询车辆的二级维护信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByCarPerTest.do")
	@ResponseBody
	public List<Map<String, Object>> selectByCarTwoLeMain(String plateNo,String color)
			throws Exception {
		return this.managerCarService.selectByCarTwoLeMain(plateNo, color);
	}

	/**
	 * 根据车牌号码和颜色查询车辆的违规违章信息
	 * 
	 * @param page
	 * @return PageResult<Map<String,Object>>
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByCarIllegal.do")
	@ResponseBody
	public List<Map<String, Object>> selectByCarIllegal(String plateNo,String color)
			throws Exception {
		return this.managerCarService.selectByCarIllegal(plateNo, color);
	}
	
}
