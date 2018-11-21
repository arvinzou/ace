package com.huacainfo.ace.backend.mq;

import com.huacainfo.ace.backend.mq.threads.LiveVisitCallBackThread;
import com.huacainfo.ace.common.kafka.KafKaConsumerAbstract;
import kafka.consumer.KafkaStream;
import scala.actors.threadpool.ExecutorService;

public class LiveVisitCallbackDealer extends KafKaConsumerAbstract {
    public LiveVisitCallbackDealer(String zookeeper, String groupId, String topic,
                                   int threads, boolean newonly) {
        super(zookeeper, groupId, topic, threads, newonly);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void dealStream(ExecutorService executor,
                              KafkaStream<byte[], byte[]> stream) {
        // TODO Auto-generated method stub
        executor.submit(new LiveVisitCallBackThread("系统消息线程", stream));
    }

}
