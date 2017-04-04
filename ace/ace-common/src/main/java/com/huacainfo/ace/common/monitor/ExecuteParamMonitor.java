package com.huacainfo.ace.common.monitor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.huacainfo.ace.common.aop.BaseAopAround;

public class ExecuteParamMonitor implements MethodBeforeAdvice,
		AfterReturningAdvice {

	Logger logger = LoggerFactory.getLogger(ExecuteParamMonitor.class);

	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		logger.debug("离开:{}，方法:{},返回内容/大小：{} ",
				BaseAopAround.getClassName(target), method.getName(),
				getReturnValueDisplay(returnValue));
	}

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		logger.debug("进入:{}，方法:{},参数：{}", BaseAopAround.getClassName(target),
				method.getName(), Arrays.asList(args));
	}

	protected Object getReturnValueDisplay(Object returnValue) {
		Object rstObject = returnValue;
		if (returnValue != null) {
			if (returnValue instanceof Collection<?>) {
				rstObject = "集合大小:" + ((Collection<?>) returnValue).size();
			} else if (returnValue instanceof Map<?, ?>) {
				rstObject = "Map大小:" + ((Map<?, ?>) returnValue).size();
			}
		}
		return rstObject;
	}
}
