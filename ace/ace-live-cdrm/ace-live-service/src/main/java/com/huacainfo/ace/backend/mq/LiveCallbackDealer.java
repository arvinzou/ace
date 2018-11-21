package com.huacainfo.ace.backend.mq;

import com.huacainfo.ace.backend.mq.threads.LiveCallBackThread;
import com.huacainfo.ace.backend.mq.threads.LiveRptCallBackThread;
import com.huacainfo.ace.common.kafka.KafKaConsumerAbstract;
import kafka.consumer.KafkaStream;
import scala.actors.threadpool.ExecutorService;

/**
 * Created by Arvin on 2018/3/1.
 */
public class LiveCallbackDealer extends KafKaConsumerAbstract {

    public LiveCallbackDealer(String zookeeper, String groupId, String topic,
                                 int threads, boolean newonly) {
        super(zookeeper, groupId, topic, threads, newonly);

    }
    @Override
    protected void dealStream(ExecutorService executor, KafkaStream<byte[], byte[]> stream) {
        executor.submit(new LiveCallBackThread("系统消息线程", stream));
    }
}
