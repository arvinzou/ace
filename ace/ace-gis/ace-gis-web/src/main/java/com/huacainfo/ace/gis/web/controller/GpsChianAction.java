package com.huacainfo.ace.gis.web.controller;

import org.apache.log4j.Logger;
import com.huacainfo.ace.gis.service.GpsChianService;
import com.huacainfo.ace.gis.vo.GpsChianQVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/gpsChian")
public class GpsChianAction extends GisBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GpsChianService gpsChianService;
	@RequestMapping(value = "/selectListByPAreaName01.do")
	@ResponseBody
	public Object selectListByPAreaName01(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName01(condition);
	}
	@RequestMapping(value = "/selectListByPAreaName02.do")
	@ResponseBody
	public Object selectListByPAreaName02(GpsChianQVo condition) throws Exception {

		return gpsChianService.selectListByPAreaName02(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName03.do")
	@ResponseBody
	public Object selectListByPAreaName03(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName03(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName04.do")
	@ResponseBody
	public Object selectListByPAreaName04(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName04(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName05.do")
	@ResponseBody
	public Object selectListByPAreaName05(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName05(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName06.do")
	@ResponseBody
	public Object selectListByPAreaName06(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName06(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName07.do")
	@ResponseBody
	public Object selectListByPAreaName07(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName07(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName08.do")
	@ResponseBody
	public Object selectListByPAreaName08(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName08(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName09.do")
	@ResponseBody
	public Object selectListByPAreaName09(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName09(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName10.do")
	@ResponseBody
	public Object selectListByPAreaName10(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName10(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName11.do")
	@ResponseBody
	public Object selectListByPAreaName11(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName11(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName12.do")
	@ResponseBody
	public Object selectListByPAreaName12(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName12(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName13.do")
	@ResponseBody
	public Object selectListByPAreaName13(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName13(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName14.do")
	@ResponseBody
	public Object selectListByPAreaName14(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName14(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName15.do")
	@ResponseBody
	public Object selectListByPAreaName15(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName15(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName16.do")
	@ResponseBody
	public Object selectListByPAreaName16(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName16(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName17.do")
	@ResponseBody
	public Object selectListByPAreaName17(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName17(condition);

	}
	@RequestMapping(value = "/selectListByPAreaName99.do")
	@ResponseBody
	public Object selectListByPAreaName99(GpsChianQVo condition) throws Exception {
		return gpsChianService.selectListByPAreaName99(condition);

	}
}
