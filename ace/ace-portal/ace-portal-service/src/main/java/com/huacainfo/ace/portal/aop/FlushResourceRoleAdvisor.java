package com.huacainfo.ace.portal.aop;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.security.spring.SecurityLoadResouceDefine;
import com.huacainfo.ace.common.web.tools.WebUtils;

public class FlushResourceRoleAdvisor extends ResoureRoleAdvisor {
	private SecurityLoadResouceDefine securityLoadResouceDefine;

	public void setSecurityLoadResouceDefine(
			SecurityLoadResouceDefine securityLoadResouceDefine) {
		this.securityLoadResouceDefine = securityLoadResouceDefine;
	}

	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] params, Object target) throws Throwable {
		List<Map<String, String>> list = securityLoadResouceDefine
				.loadResourceDefine();
		WebUtils.flushRoleResourceCache(redisTemplateString, list);
	}

}
