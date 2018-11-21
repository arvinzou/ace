package com.huacainfo.ace.backend.mq.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.live.service.WWWService;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class LiveVisitCallBackThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(LiveVisitCallBackThread.class);

    private KafkaStream<byte[], byte[]> stream = null;
    private WWWService wwwService;

    public LiveVisitCallBackThread(String name, KafkaStream<byte[], byte[]> stream) {
        super(name);
        this.stream = stream;
        this.init();
    }

    public LiveVisitCallBackThread(ThreadGroup group, String name, KafkaStream<byte[], byte[]> stream) {
        super(group, name);
        this.stream = stream;
        this.init();
    }

    private void init() {
        this.wwwService = (WWWService) SpringUtils.getBean("wwwService");
    }

    @Override
    public void run() {
        LOGGER.debug("任务上报消费者开始处理消息，处理线程名称：", this.getName());
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            byte[] bytes = it.next().message();
            JSONObject o = JSON.parseObject(new String(bytes));
            @SuppressWarnings("unchecked")
            Map<String, String> data = JSON.parseObject(o.get("content").toString(), Map.class);
            try {
                doCallBack(data);
            } catch (Exception ex) {
                LOGGER.error("处理失败", ex);
            }
        }
    }

    public void doCallBack(Map<String, String> data) {
        LOGGER.info("接收消息->{}", data);
        try {
            this.wwwService.updateRptVisitNum(data.get("id"));
        } catch (Exception e) {
            LOGGER.error("系统出错{}", e);
        }
    }

}
