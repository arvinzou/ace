package com.huacainfo.ace.portal.web.controller;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;

public class PortalBaseController extends BaseController implements
		Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthorityService authorityService;
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
	protected SingleResult<UserProp> getCurUserPropByOpenId() throws Exception{
		String openId=null;
		if(CommonUtils.isNotEmpty(this.getCurWxUser())){
			openId=this.getCurWxUser().getOpenId();
		}
		if(CommonUtils.isNotEmpty(this.getCurUserinfo())){
			openId=this.getCurUserinfo().getOpenid();
		}
		SingleResult<UserProp> rst=authorityService.getCurUserPropByOpenId(openId);
		return rst;
	}
	
}
