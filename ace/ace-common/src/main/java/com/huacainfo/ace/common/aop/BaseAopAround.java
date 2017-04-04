package com.huacainfo.ace.common.aop;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseAopAround implements MethodInterceptor {
	private static final Map<String, String> PROXY_NAME_MAP = new ConcurrentHashMap<String, String>();
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected int infoExceedMillsecond = 100;
	protected int warnExceedMillsecond = 200;
	protected int errorExceedMillsecond = 500;

	protected String getMethodName(MethodInvocation invocation) {
		String className = getInvocationClassName(invocation);
		String methodName = className + "." + invocation.getMethod().getName();
		return methodName;
	}

	protected String getInvocationClassName(MethodInvocation invocation) {
		String className = getClassName(invocation.getThis());
		return className;
	}

	public int getInfoExceedMillsecond() {
		return infoExceedMillsecond;
	}

	public void setInfoExceedMillsecond(int infoExceedMillsecond) {
		this.infoExceedMillsecond = infoExceedMillsecond;
	}

	public int getWarnExceedMillsecond() {
		return warnExceedMillsecond;
	}

	public void setWarnExceedMillsecond(int warnExceedMillsecond) {
		this.warnExceedMillsecond = warnExceedMillsecond;
	}

	public int getErrorExceedMillsecond() {
		return errorExceedMillsecond;
	}

	public void setErrorExceedMillsecond(int errorExceedMillsecond) {
		this.errorExceedMillsecond = errorExceedMillsecond;
	}

	public static String getClassName(Object target) {
		String className = target.getClass().getName();
		if (className.startsWith("com.alibaba.dubbo.common.bytecode.proxy")
				|| className.startsWith("com.sun.proxy")) {
			if (PROXY_NAME_MAP.containsKey(className)) {
				className = PROXY_NAME_MAP.get(className);
			} else {
				Class<?>[] interfaces = target.getClass().getInterfaces();
				if (interfaces.length > 0) {
					String proxyName = "proxy:"
							+ getInterfaceName(className, interfaces);
					PROXY_NAME_MAP.put(className, proxyName);
					className = proxyName;
				}
			}
		}
		return className;
	}

	private static String getInterfaceName(String className,
			Class<?>[] interfaces) {
		if (className.startsWith("com.alibaba.dubbo.common.bytecode.proxy")) {
			className = interfaces[interfaces.length - 1].getName();
		} else {
			for (Class<?> cls : interfaces) {
				className = cls.getName();
				if ((!className.contains("proxy"))
						&& !className.contains("java.io.Serializable")) {
					break;
				}
			}
		}
		return className;
	}
}
