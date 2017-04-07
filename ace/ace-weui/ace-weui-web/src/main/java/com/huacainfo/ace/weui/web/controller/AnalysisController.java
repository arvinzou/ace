package com.huacainfo.ace.weui.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.weui.service.AnalysisService;

@Controller
@RequestMapping("/www/anslysis")
public class AnalysisController extends WeuiBaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AnalysisService analysisService;

	@RequestMapping(value = "/query.do")
	@ResponseBody
	public ListResult<Map<String,Object>> query(
			 String reportId,int start,int limit)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if(CommonUtils.isBlank(start)){
			start=0;
		}
		if(CommonUtils.isBlank(limit)){
			limit=10;
		}
		return analysisService.query(condition, reportId,start,limit);
	}	
}
