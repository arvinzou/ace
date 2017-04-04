package com.huacainfo.ace.common.kafka;

import java.util.List;

public interface KafkaProducerService {
	

	public void sendMsg(String topicName, String data);
	public void sendMsg(String topicName, Object data);
	public void sendMsg(String topicName, KafkaMessage<?> msg);

	/**
	 * 指定分区关键字能保证全局消息顺序
	 * 
	 * @param topicName
	 * @param partitionKey
	 * @param data
	 */
	public void sendMsg(String topicName, String partitionKey, Object data);

	public void sendMsg(String topicName, List<KafkaMessage<?>> msgList);

}
