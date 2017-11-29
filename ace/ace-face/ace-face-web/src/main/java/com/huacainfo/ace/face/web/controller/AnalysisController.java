package com.huacainfo.ace.face.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.face.service.AnalysisService;

@Controller
@RequestMapping("/anslysis")
public class AnalysisController extends FaceBaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AnalysisService analysisService;

	@RequestMapping(value = "/query.do")
	@ResponseBody
	public ListResult<Map<String,Object>> query(
			 String reportId)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		return analysisService.query(condition, reportId,0,0);
	}	
}
