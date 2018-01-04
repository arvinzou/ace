package com.huacainfo.ace.backend.mq.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.live.model.LiveMsg;
import com.huacainfo.ace.live.service.LiveMsgService;
import com.huacainfo.ace.live.web.websocket.WebSocketSub;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
            Map<String, String> data = JSON.parseObject(o.get("content").toString(), Map.class);
            try {
                doCallBack(data);
            } catch (Exception ex) {
                LOGGER.error("处理失败", ex);
            }
        }
    }

    public void doCallBack(Map<String, String> data) {

        String rid = data.get("rid");
        String message = data.get("message");
        LOGGER.info("=========================接收消息 rid->{}", rid);
        LOGGER.info("=========================接收消息 message->{}", message);
        LOGGER.info("=========================WebSocketSub.rooms->{}", WebSocketSub.rooms.isEmpty());
        try {
            if (WebSocketSub.rooms.get(rid) != null) {

                for (WebSocketSub item : WebSocketSub.rooms.get(rid)) {
                    LOGGER.info("=========================发送消息->{}", message);
                    try {
                        item.sendMessage(message);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("系统出错{}", e);
        }
    }

}
