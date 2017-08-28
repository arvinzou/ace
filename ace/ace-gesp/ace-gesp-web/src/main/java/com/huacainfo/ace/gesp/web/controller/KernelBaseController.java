package com.huacainfo.ace.gesp.web.controller;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.web.controller.BaseController;

public class KernelBaseController extends BaseController implements
		Serializable {
	private static final long serialVersionUID = 1L;

	public static Map<String,Object> getParamMap(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = request.getParameter(key);
			map.put(key, value);
		}
		return map;

	}
	
	
	public String json(Object obj) {
		if (obj == null)
			return "{}";
		if (obj instanceof String)
			return (String) obj;
		else
			return JSON.toJSONString(obj);
	}
}
