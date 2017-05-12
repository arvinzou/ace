package com.huacainfo.ace.operana.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.operana.service.ChartService;

@Controller
@RequestMapping("/chart")
public class ChartController extends OperanaBaseController {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChartService chartService;

	@RequestMapping(value = "/chart1.do")
	@ResponseBody
	public Map<String,Object> chart1(String meetingId, String topicId, String normId) throws Exception {
		return this.chartService.chart1(meetingId, topicId, normId);

	}

	@RequestMapping(value = "/chart2.do")
	@ResponseBody
	public Map<String,Object> chart2(String meetingId, String topicId, String normId) throws Exception {
		return this.chartService.chart2(meetingId, topicId, normId);
	}

	@RequestMapping(value = "/chart3.do")
	@ResponseBody
	public Map<String,Object> chart3(String meetingId, String topicId, String normId) throws Exception {
		return this.chartService.chart3(meetingId, topicId, normId);
	}

	@RequestMapping(value = "/chart4.do")
	@ResponseBody
	public Map<String,Object> chart4(String meetingId, String topicId, String normId) throws Exception {
		return this.chartService.chart4(meetingId, topicId, normId);
	}

}
