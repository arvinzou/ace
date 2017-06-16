package com.huacainfo.ace.backend.mq.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.portal.service.SysInfoService;

public class SysInfoCallBackThread extends Thread {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysInfoCallBackThread.class);

	private KafkaStream<byte[], byte[]> stream = null;
	private SysInfoService sysInfoService;
	public SysInfoCallBackThread(String name, KafkaStream<byte[], byte[]> stream) {
		super(name);
		this.stream = stream;
		this.init();
	}

	public SysInfoCallBackThread(ThreadGroup group, String name, KafkaStream<byte[], byte[]> stream) {
		super(group, name);
		this.stream = stream;
		this.init();
	}
	private void init() {
		this.sysInfoService = (SysInfoService) SpringUtils.getBean("sysInfoService");
	}
	public void run() {
		LOGGER.debug("任务上报消费者开始处理消息，处理线程名称：", this.getName());
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while (it.hasNext()) {
			byte[] bytes = it.next().message();
			JSONObject o = JSON.parseObject(new String(bytes));
			@SuppressWarnings("unchecked")
			Map<String, String> data = JSON.parseObject(o.get("content").toString(), Map.class);
			try {
				doCallBack(data);
			} catch (Exception ex) {
				LOGGER.error("处理失败", ex);
			}
		}
	}

	public void doCallBack(Map<String, String> data) {
		List<String> address = new ArrayList<String>();
		address.add(data.get("to"));
		LOGGER.info("接收消息->{}", data);
		try {
			sysInfoService.sendBatchEmail(data.get("subject"), data.get("content"), address);
		} catch (MessagingException e) {
			LOGGER.error("系统出错{}", e);
		}
	}

}
