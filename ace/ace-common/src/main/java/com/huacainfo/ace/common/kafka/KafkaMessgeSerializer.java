package com.huacainfo.ace.common.kafka;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class KafkaMessgeSerializer implements Encoder<KafkaMessage<?>> {
	private Logger logger = LoggerFactory
			.getLogger(KafkaMessgeSerializer.class);

	public KafkaMessgeSerializer() {

	}

	public KafkaMessgeSerializer(VerifiableProperties prop) {

	}

	public byte[] toBytes(KafkaMessage<?> data) {
		try {
			return JSONObject.toJSONString(data).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("转换失败", e);
		}
		return null;

	}

}
