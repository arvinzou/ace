package com.huacainfo.ace.common.kafka;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class KafkaMessage<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 消息类型
	 */
	private String partitionKey = null;
	/**
	 * 消息内容
	 */
	private T content;
	/**
	 * 发送时间
	 */
	private Date sendTime = new Date();

	public KafkaMessage() {

	}

	public KafkaMessage(T content) {
		this.content = content;
	}

	public KafkaMessage(String partitionKey, T content) {
		this(content);
		this.partitionKey = partitionKey;
	}

	/**
	 * 消息类型
	 */
	public String getPartitionKey() {
		return partitionKey;
	}

	/**
	 * 消息类型
	 */
	public void setPartitionKey(String partitionKey) {
		this.partitionKey = partitionKey;
	}

	/**
	 * 消息内容
	 */
	public T getContent() {
		return content;
	}

	/**
	 * 消息内容
	 */
	public void setContent(T content) {
		this.content = content;
	}

	/**
	 * 发送时间
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 发送时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
