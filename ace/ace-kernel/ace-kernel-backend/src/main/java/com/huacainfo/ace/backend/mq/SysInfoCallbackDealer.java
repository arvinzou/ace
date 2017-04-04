package com.huacainfo.ace.backend.mq;

import kafka.consumer.KafkaStream;
import scala.actors.threadpool.ExecutorService;

import com.huacainfo.ace.backend.mq.threads.SysInfoCallBackThread;
import com.huacainfo.ace.common.kafka.KafKaConsumerAbstract;

public class SysInfoCallbackDealer extends KafKaConsumerAbstract {
	public SysInfoCallbackDealer(String zookeeper, String groupId, String topic,
			int threads, boolean newonly) {
		super(zookeeper, groupId, topic, threads, newonly);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void dealStream(ExecutorService executor,
			KafkaStream<byte[], byte[]> stream) {
		// TODO Auto-generated method stub
		executor.submit(new SysInfoCallBackThread("系统消息线程", stream));
	}

}
