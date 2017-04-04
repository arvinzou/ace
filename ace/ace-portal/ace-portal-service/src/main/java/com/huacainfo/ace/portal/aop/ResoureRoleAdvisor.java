package com.huacainfo.ace.portal.aop;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.data.redis.core.RedisOperations;

import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.web.tools.WebUtils;

public class ResoureRoleAdvisor implements AfterReturningAdvice, CommonKeys {
	protected RedisOperations<String, String> redisTemplateString;

	@SuppressWarnings("unchecked")
	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] params, Object target) throws Throwable {
		if (returnValue != null && returnValue instanceof List) {
			List<Map<String, String>> list = (List<Map<String, String>>) returnValue;
			WebUtils.flushRoleResourceCache(redisTemplateString, list);
		}

	}

	public void setRedisTemplateString(
			RedisOperations<String, String> redisTemplateString) {
		this.redisTemplateString = redisTemplateString;
	}
}
