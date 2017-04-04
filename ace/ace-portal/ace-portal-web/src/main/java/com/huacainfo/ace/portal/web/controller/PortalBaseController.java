package com.huacainfo.ace.portal.web.controller;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.huacainfo.ace.common.web.controller.BaseController;

public class PortalBaseController extends BaseController implements
		Serializable {
	private static final long serialVersionUID = 1L;

	
	public static Map<String,Object> getParamMap(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		@SuppressWarnings("unchecked")
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = request.getParameter(key);
			map.put(key, value);
		}
		return map;

	}
	
}
