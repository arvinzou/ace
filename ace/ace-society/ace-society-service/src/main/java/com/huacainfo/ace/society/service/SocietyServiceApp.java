package com.huacainfo.ace.society.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SocietyServiceApp {
	private static Logger LOGGER = LoggerFactory
			.getLogger(SocietyServiceApp.class);

	public static void main(String[] args) {
		AbstractApplicationContext ctx = null;
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			LOGGER.info("Society服务启动成功");
			synchronized (ctx) {
				ctx.wait();
			}
		} catch (Exception ex) {
			LOGGER.error("Society服务启动失败", ex);
			if (ctx != null) {
				ctx.destroy();
			}
			System.exit(1);
		}
	}
}
