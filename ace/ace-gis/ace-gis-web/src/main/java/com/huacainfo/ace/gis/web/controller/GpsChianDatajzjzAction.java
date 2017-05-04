package com.huacainfo.ace.gis.web.controller;

import org.apache.log4j.Logger;

import com.huacainfo.ace.gis.service.GpsChianDatajzjzService;
import com.huacainfo.ace.gis.vo.GpsChianQVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/gpsChianDatajzjz")
public class GpsChianDatajzjzAction extends GisBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GpsChianDatajzjzService gpsChianDatajzjzService;
	@RequestMapping(value = "/selectListByPAreaName01.do")
	@ResponseBody
	public Object selectListByPAreaName01(GpsChianQVo condition) throws Exception {
		return gpsChianDatajzjzService.selectListByPAreaName01(condition);

	}

	@RequestMapping(value = "/selectListByPAreaName03.do")
	@ResponseBody
	public Object selectListByPAreaName03(GpsChianQVo condition) throws Exception {
		return gpsChianDatajzjzService.selectListByPAreaName03(condition);

	}

	@RequestMapping(value = "/selectListByPAreaName05.do")
	@ResponseBody
	public Object selectListByPAreaName05(GpsChianQVo condition) throws Exception {
		return gpsChianDatajzjzService.selectListByPAreaName05(condition);

	}

	@RequestMapping(value = "/selectListByPAreaName12.do")
	@ResponseBody
	public Object selectListByPAreaName12(GpsChianQVo condition) throws Exception {
		return gpsChianDatajzjzService.selectListByPAreaName12(condition);

	}

	@RequestMapping(value = "/selectListByPAreaName14.do")
	@ResponseBody
	public Object selectListByPAreaName14(GpsChianQVo condition) throws Exception {
		return gpsChianDatajzjzService.selectListByPAreaName14(condition);

	}
}
