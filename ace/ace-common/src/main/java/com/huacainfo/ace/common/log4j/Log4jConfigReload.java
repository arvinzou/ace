package com.huacainfo.ace.common.log4j;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jConfigReload {
	private int interval = 60 * 1000;
	private static final Logger logger = LoggerFactory
			.getLogger(Log4jConfigReload.class);

	public Log4jConfigReload() {
		this.loadConfig();
	}

	/**
	 * log4j日志自动加载
	 *
	 * @param interval
	 *            自动加载时间(ms)
	 */
	public Log4jConfigReload(int interval) {
		this.interval = interval;
		this.loadConfig();
	}

	public void loadConfig() {
		URL url = Log4jConfigReload.class.getClassLoader().getResource(
				"log4j.properties");
		if (url == null) {
			url = Log4jConfigReload.class.getClassLoader().getResource(
					"log4j.xml");
		}
		if (url != null) {
			String log4jPath = url.getPath();
			// 间隔特定时间，检测文件是否修改，自动重新读取配置
			PropertyConfigurator.configureAndWatch(log4jPath, this.interval);
			logger.debug("log4j配置文件定时检测启动成功，文件路径：{}，间隔时间：{} ", log4jPath,
					interval);
		} else {
			logger.debug("log4j配置文件定时检测启动失败，未找到配置文件 ");
		}
	}
}
