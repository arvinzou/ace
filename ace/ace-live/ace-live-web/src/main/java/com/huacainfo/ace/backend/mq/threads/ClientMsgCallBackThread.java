package com.huacainfo.ace.backend.mq.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.live.web.websocket.MyWebSocket;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

public class ClientMsgCallBackThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientMsgCallBackThread.class);

    private KafkaStream<byte[], byte[]> stream = null;


    public ClientMsgCallBackThread(String name, KafkaStream<byte[], byte[]> stream) {
        super(name);
        this.stream = stream;
        this.init();
    }

    public ClientMsgCallBackThread(ThreadGroup group, String name, KafkaStream<byte[], byte[]> stream) {
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
        try{
            JSONObject header=new JSONObject();
            header.put("cmd",(String) data.get("cmd"));
            JSONObject body=new JSONObject();
            body.put("body",data.get("body"));
            JSONObject message=new JSONObject();
            message.put("header",header);
            message.put("body",body);
            this.sendMessage(message.toJSONString(),(String)data.get("rid"));
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }
    private void sendMessage(String message, String rid) throws Exception {
        LOGGER.debug("{} {}", rid, message);
        if (MyWebSocket.rooms.get(rid) == null) {
            CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
            MyWebSocket.rooms.put(rid, webSocketSet);
            LOGGER.debug("create new room rid:{}", rid);
        }
        for (MyWebSocket  item : MyWebSocket.rooms.get(rid)) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                continue;
            }
        }
    }
}
