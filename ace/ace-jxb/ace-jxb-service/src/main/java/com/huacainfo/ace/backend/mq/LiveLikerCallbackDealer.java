package com.huacainfo.ace.backend.mq;

import com.huacainfo.ace.backend.mq.threads.LiveCmtCallBackThread;
import com.huacainfo.ace.backend.mq.threads.LiveLikerCallBackThread;
import com.huacainfo.ace.common.kafka.KafKaConsumerAbstract;
import kafka.consumer.KafkaStream;
import scala.actors.threadpool.ExecutorService;

public class LiveLikerCallbackDealer extends KafKaConsumerAbstract {
    public LiveLikerCallbackDealer(String zookeeper, String groupId, String topic,
                                   int threads, boolean newonly) {
        super(zookeeper, groupId, topic, threads, newonly);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void dealStream(ExecutorService executor,
                              KafkaStream<byte[], byte[]> stream) {
        // TODO Auto-generated method stub
        executor.submit(new LiveLikerCallBackThread("系统消息线程", stream));
    }

}
