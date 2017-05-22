package com.huacainfo.ace.common.web.controller;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.WxUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.CommonKeys;

public class BaseController implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisOperations<String, Object> redisTemplate;

	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	protected Object getSession(String name) {
		return getRequest().getSession().getAttribute(name);
	}

	protected UserProp getCurUserProp() {
		Object object = getRequest().getSession().getAttribute(
				CommonKeys.SESSION_USERPROP_KEY);
		return (UserProp) object;
	}

	protected Map<String, Object> getParams() {
		Map<String, Object> rst = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Enumeration<String> enu = this.getRequest().getParameterNames();
		while (enu.hasMoreElements()) {
			String key =  enu.nextElement();
			rst.put(key,  this.getRequest().getParameter(key));
		}
		this.logger.debug("params:"+rst);
		return rst;
	}

	protected WxUser getCurWxUser() {
		String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
		JSONObject userinfo=(JSONObject)this.redisTemplate.opsForValue().get(_3rd_session);
		WxUser wxUser = JSON.parseObject(userinfo.toString(),WxUser.class);
		return wxUser;
	}

}
