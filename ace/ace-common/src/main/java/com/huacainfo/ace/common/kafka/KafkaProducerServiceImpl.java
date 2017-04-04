package com.huacainfo.ace.common.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerServiceImpl implements KafkaProducerService {
	private Logger logger = LoggerFactory
			.getLogger(KafkaProducerServiceImpl.class);
	private Properties prop = null;
	private Producer<String, KafkaMessage<?>> producer;

	public KafkaProducerServiceImpl(Map<String, String> configData) {
		prop = new Properties();
		prop.putAll(configData);
		if (!prop.containsKey("serializer.class")) {
			prop.put("serializer.class",
					"com.huacainfo.ace.common.kafka.KafkaMessgeSerializer");
		}
		if (!prop.contains("partitioner.class")) {
			prop.put("partitioner.class",
					"com.huacainfo.ace.common.kafka.KafkaMessagePartitioner");
		}
		if (!prop.contains("acks")) {
			prop.put("acks", "-1");
		}
		ProducerConfig config = new ProducerConfig(prop);
		producer = new Producer<String, KafkaMessage<?>>(config);
		logger.info("初始化完成，配置属性：{}", prop);
	}

	public void sendMsg(String topicName, String data) {
		KafkaMessage<String> kafkaMessage = new KafkaMessage<String>(data);
		sendMsg(topicName, kafkaMessage);
	}

	@Override
	public void sendMsg(String topicName, Object data) {
		KafkaMessage<Object> kafkaMessage = new KafkaMessage<Object>(data);
		sendMsg(topicName, kafkaMessage);
	}

	@Override
	public void sendMsg(String topicName, KafkaMessage<?> msg) {
		if (msg.getContent() != null) {
			KeyedMessage<String, KafkaMessage<?>> km = new KeyedMessage<String, KafkaMessage<?>>(
					topicName, msg);
			producer.send(km);
		} else {
			logger.error("发生消息的内容不能为空");
		}
	}

	@Override
	public void sendMsg(String topicName, String partitionKey, Object data) {
		KafkaMessage<Object> kafkaMessage = new KafkaMessage<Object>(
				partitionKey, data);
		sendMsg(topicName, kafkaMessage);
	}

	@Override
	public void sendMsg(String topicName, List<KafkaMessage<?>> msgList) {
		List<KeyedMessage<String, KafkaMessage<?>>> kmList = new ArrayList<KeyedMessage<String, KafkaMessage<?>>>();
		for (KafkaMessage<?> msg : msgList) {
			KeyedMessage<String, KafkaMessage<?>> km = new KeyedMessage<String, KafkaMessage<?>>(
					topicName, msg);
			kmList.add(km);
		}
		producer.send(kmList);
	}

}
