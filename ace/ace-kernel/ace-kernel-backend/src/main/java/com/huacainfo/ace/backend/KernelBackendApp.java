package com.huacainfo.ace.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KernelBackendApp {

	private static Logger LOGGER = LoggerFactory
			.getLogger(KernelBackendApp.class);

	public static void main(String[] args) {
		AbstractApplicationContext ctx = null;
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			LOGGER.info("系统消息后台程序启动成功");
			synchronized (ctx) {
				ctx.wait();
			}
		} catch (Exception ex) {
			LOGGER.error("系统消息后台程序启动失败", ex);
			if (ctx != null) {
				ctx.destroy();
			}
			System.exit(1);
		}

	}
}
