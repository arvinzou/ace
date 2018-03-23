package com.huacainfo.ace.backend.mq.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.jxb.model.Live;
import com.huacainfo.ace.jxb.service.LiveRptService;
import com.huacainfo.ace.jxb.service.LiveService;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.actors.threadpool.Callable;

import java.util.Map;

/**
 * Created by Arvin on 2018/3/1.
 */
public class LiveCallBackThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(LiveCallBackThread.class);

    private KafkaStream<byte[], byte[]> stream = null;
    private LiveService jxbService;

    public LiveCallBackThread(String name, KafkaStream<byte[], byte[]> stream) {
        super(name);
        this.stream = stream;
        this.init();
    }

    public LiveCallBackThread(ThreadGroup group, String name, KafkaStream<byte[], byte[]> stream) {
        super(group, name);
        this.stream = stream;
        this.init();
    }

    private void init() {
        this.jxbService = (LiveService) SpringUtils.getBean("jxbService");
    }


    @Override
    public void run() {
        LOGGER.debug("任务上报消费者开始处理消息，处理线程名称：", this.getName());
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            byte[] bytes = it.next().message();
            JSONObject o = JSON.parseObject(new String(bytes));
            Map<String, String> data = JSON.parseObject(o.get("content").toString(), Map.class);
            try {
                Map<String, Object> params = JsonUtil.toMap(data.get("jsons"));
                String openid = (String) params.get("openid");
                Live jxb = JsonUtil.toObject(data.get("jsons"), Live.class);

                MessageResponse response = jxbService.insertLive(openid, jxb);

                LOGGER.info("{}", response.getErrorMessage());
            } catch (Exception ex) {
                LOGGER.error("处理失败", ex);
            }
        }

    }


}
