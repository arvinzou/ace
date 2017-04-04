package com.huacainfo.ace.common.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * 群消息分片
 * 
 * @author leishaojie
 * 
 */
public class KafkaMessagePartitioner implements Partitioner<KafkaMessage<?>> {

	public KafkaMessagePartitioner() {

	}

	public KafkaMessagePartitioner(VerifiableProperties prop) {

	}

	public int partition(KafkaMessage<?> data, int totalPartition) {
		int rst = data.getContent().hashCode() % totalPartition;
		if (data.getPartitionKey() != null) {
			rst = data.hashCode() % totalPartition;
		}
		return rst;
	}
}
