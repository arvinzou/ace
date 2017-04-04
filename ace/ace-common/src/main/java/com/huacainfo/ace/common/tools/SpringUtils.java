package com.huacainfo.ace.common.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext context = null;

	public void setApplicationContext(ApplicationContext ctx) {
		SpringUtils.context = ctx;
	}

	public static Object getBean(String beanName) {
		if (context.containsBean(beanName)) {
			Object obj = context.getBean(beanName);
			return obj;
		}
		return null;

	}

	public static ApplicationContext getContext() {
		return context;
	}
}
