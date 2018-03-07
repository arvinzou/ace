package com.huacainfo.ace.woc.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.huacainfo.ace.woc.service.AnalysisService;

@Controller
@RequestMapping("/v1")
public class TokenController extends WocBaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AnalysisService analysisService;

	@RequestMapping(value = "/login")
	public ResponseEntity login(String username,String password)
			throws Exception {
		Map<String,Object> rst=new HashMap<>();
		rst.put("access_token","2YotnFZFEjr1zCsicMWpAA");
		rst.put("token_type","example");
		rst.put("expires_in",7200);
		rst.put("refresh_token","tGzv3JOkF0XG5Qx2TlKWIA");
		rst.put("scope","scope");
		return new ResponseEntity<>(rst, HttpStatus.OK);
	}	
}
