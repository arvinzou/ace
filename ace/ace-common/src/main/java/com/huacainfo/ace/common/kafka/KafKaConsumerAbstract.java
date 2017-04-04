/**
 * 
 */
package com.huacainfo.ace.common.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.actors.threadpool.ExecutorService;
import scala.actors.threadpool.Executors;

/**
 * @author leishaojie
 * 
 */
public abstract class KafKaConsumerAbstract {
	private Logger logger = LoggerFactory
			.getLogger(KafKaConsumerAbstract.class);
	private final ConsumerConnector consumer;
	private final String topic;
	private ExecutorService executor;
	protected int threads = 10;

	public KafKaConsumerAbstract(String zookeeper, String groupId,
			String topic, int threads, boolean newonly) {
		logger.info("创建Consumer开始,zookeeper:" + zookeeper + ",groupId:"
				+ groupId + ",topic:" + topic);
		consumer = kafka.consumer.Consumer
				.createJavaConsumerConnector(createConsumerConfig(zookeeper,
						groupId, newonly));
		this.topic = topic;
		this.threads = threads;
		logger.info(
				"创建Consumer成功,zookeeper: {} ,groupId:{},topic:{},newOnly:{}",
				zookeeper, groupId, topic, newonly);
	}

	public void shutdown() {
		if (consumer != null)
			consumer.shutdown();
		if (executor != null)
			executor.shutdown();
	}

	protected abstract void dealStream(ExecutorService executor,
			KafkaStream<byte[], byte[]> stream);

	public void run() {
		logger.info("启动Kafka消费者线程");
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, threads);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
				.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
		logger.info("启动Kafka消费者线程，streams数量：{}", streams.size());
		// now launch all the threads
		executor = Executors.newFixedThreadPool(threads);
		// now create an object to consume the messages
		for (final KafkaStream<byte[], byte[]> stream : streams) {
			dealStream(executor, stream);
		}
	}

	private static ConsumerConfig createConsumerConfig(String zookeeper,
			String groupId, boolean newOnly) {
		Properties props = new Properties();
		props.put("zookeeper.connect", zookeeper);
		props.put("group.id", groupId);
		props.put("zookeeper.session.timeout.ms", "12000");
		props.put("zookeeper.sync.time.ms", "12000");
		props.put("auto.commit.interval.ms", "12000");
		props.put("auto.commit.enable", "true");
		props.put("auto.offset.reset", newOnly ? "largest" : "smallest");
		return new ConsumerConfig(props);
	}

}
