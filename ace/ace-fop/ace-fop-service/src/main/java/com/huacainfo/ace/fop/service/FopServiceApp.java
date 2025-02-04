package com.huacainfo.ace.fop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FopServiceApp {
	private static Logger LOGGER = LoggerFactory
			.getLogger(com.huacainfo.ace.fop.service.FopServiceApp.class);

	public static void main(String[] args) {
		AbstractApplicationContext ctx = null;
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			LOGGER.info("IOP服务启动成功");
			synchronized (ctx) {
				ctx.wait();
			}
		} catch (Exception ex) {
			LOGGER.error("IOP服务启动失败", ex);
			if (ctx != null) {
				ctx.destroy();
			}
			System.exit(1);
		}

	}
}
