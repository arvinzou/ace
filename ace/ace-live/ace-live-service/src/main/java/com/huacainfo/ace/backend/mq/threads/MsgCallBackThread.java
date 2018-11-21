package com.huacainfo.ace.backend.mq.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.portal.service.BackendService;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MsgCallBackThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsgCallBackThread.class);

    private KafkaStream<byte[], byte[]> stream = null;


    public MsgCallBackThread(String name, KafkaStream<byte[], byte[]> stream) {
        super(name);
        this.stream = stream;
        this.init();
    }

    public MsgCallBackThread(ThreadGroup group, String name, KafkaStream<byte[], byte[]> stream) {
        super(group, name);
        this.stream = stream;
        this.init();
    }

    private void init() {

    }

    @Override
    public void run() {
        LOGGER.debug("任务上报消费者开始处理消息，处理线程名称：", this.getName());
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            byte[] bytes = it.next().message();
            JSONObject o = JSON.parseObject(new String(bytes));
            @SuppressWarnings("unchecked")
            Map<String, Object> data = JSON.parseObject(o.get("content").toString(), Map.class);
            try {
                doCallBack(data);
            } catch (Exception ex) {
                LOGGER.error("处理失败", ex);
            }
        }
    }

    public void doCallBack(Map<String, Object> data) {
        LOGGER.error("=======================================doCallBack -> {}", data);
        BackendService service= (BackendService)SpringUtils.getBean((String) data.get("service"));
        try {
            service.service(data);
        } catch (Exception ex) {
            LOGGER.error("处理失败", ex);
        }
    }

}
