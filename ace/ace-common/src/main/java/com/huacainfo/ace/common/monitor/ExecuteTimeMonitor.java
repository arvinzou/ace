package com.huacainfo.ace.common.monitor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import com.huacainfo.ace.common.aop.BaseAopAround;

public class ExecuteTimeMonitor extends BaseAopAround {
	public ExecuteTimeMonitor() {
		super();
		infoExceedMillsecond = 50;
		warnExceedMillsecond = 100;
		errorExceedMillsecond = 200;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		Object result = null;
		result = invocation.proceed();
		clock.stop();
		doExecuteTimeLog(clock.getTotalTimeMillis(), invocation);
		return result;
	}

	protected void doExecuteTimeLog(Long executeTime,
			MethodInvocation invocation) {
		if (executeTime <= infoExceedMillsecond) {
			logger.debug("方法名：{},执行时长：{}", getMethodName(invocation),
					executeTime);
		} else if (executeTime <= warnExceedMillsecond) {
			logger.info("方法名：{},执行时长：{}", getMethodName(invocation),
					executeTime);
		} else if (executeTime <= errorExceedMillsecond) {
			logger.warn("方法名：{},执行时长：{}", getMethodName(invocation),
					executeTime);
		} else {
			logger.error("方法名：{},执行时长：{}", getMethodName(invocation),
					executeTime);
		}
	}
}
