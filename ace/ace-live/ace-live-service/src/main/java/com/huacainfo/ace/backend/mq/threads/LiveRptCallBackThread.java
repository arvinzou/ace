package com.huacainfo.ace.backend.mq.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.model.LiveRpt;
import com.huacainfo.ace.live.service.LiveRptService;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class LiveRptCallBackThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(LiveRptCallBackThread.class);

    private KafkaStream<byte[], byte[]> stream = null;
    private LiveRptService liveRptService;

    public LiveRptCallBackThread(String name, KafkaStream<byte[], byte[]> stream) {
        super(name);
        this.stream = stream;
        this.init();
    }

    public LiveRptCallBackThread(ThreadGroup group, String name, KafkaStream<byte[], byte[]> stream) {
        super(group, name);
        this.stream = stream;
        this.init();
    }

    private void init() {

        this.liveRptService = (LiveRptService) SpringUtils.getBean("liveRptService");
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
            JSONObject json = JSON.parseObject(data.get("jsons"));
            LiveRpt o = JSON.parseObject(((JSONObject) json.get("rpt")).toJSONString(), LiveRpt.class);
            List<LiveImg> imgs = JSON.parseArray(((JSONObject) json.get("imgs")).toJSONString(), LiveImg.class);
            MessageResponse rst = this.liveRptService.insertLiveRpt(o, imgs);
            LOGGER.info("{}", rst.getErrorMessage());
        } catch (Exception e) {
            LOGGER.error("系统出错{}", e);
        }
    }

}
