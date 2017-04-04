package com.ace.test.kafka;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.UserProp;

@ContextConfiguration(locations = { "classpath:spring/spring-mp-common-tester.xml" })
public class CommonToolsTester extends AbstractJUnit4SpringContextTests {
	Logger logger = LoggerFactory.getLogger(CommonToolsTester.class);

	private RedisTemplate<String, String> redisTemplate;

	
	// @Test
	/*
	 * public void testRedisAccess() { Object obj =
	 * this.applicationContext.getBean("redisTemplate"); RedisOperations
	 * redisTemplate = (RedisOperations) obj;
	 * redisTemplate.opsForValue().set("1234567890", "0123456789");
	 * System.out.println(redisTemplate.opsForValue().get("1234567890"));
	 * RedisTemplate<String, String> redisTemplateString =
	 * (RedisTemplate<String, String>) this.applicationContext
	 * .getBean("redisTemplateString");
	 * redisTemplateString.opsForValue().set("12345678901", "10123456789");
	 * System.out.println(redisTemplate.opsForValue().get("12345678901")); }
	 */
	/*
	 * @Test public void testProducter() { Object obj =
	 * this.applicationContext.getBean("kafkaProducerService");
	 * KafkaProducerService kafkaProducerService = (KafkaProducerService) obj;
	 * kafkaProducerService.sendMsg("MSP_AUDIT_OUTP_NEW", "3651,522635"); }
	 */
	@Test
	public void testEngineProducter() throws InterruptedException {
		Object obj = this.applicationContext.getBean("kafkaProducerService");
		KafkaProducerService kafkaProducerService = (KafkaProducerService) obj;
		Map<String, String> data = new HashMap<String, String>();
		data.put("to", "sss");
		data.put("subject", "bb");
		data.put("content", "cc");
		kafkaProducerService.sendMsg("GESP_SYS_INFO",data);
		

	}

}
