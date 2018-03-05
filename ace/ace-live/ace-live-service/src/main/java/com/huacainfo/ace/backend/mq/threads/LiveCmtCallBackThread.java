package com.huacainfo.ace.backend.mq.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.live.model.LiveCmt;
import com.huacainfo.ace.live.service.LiveCmtService;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author
 */

public class LiveCmtCallBackThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(LiveCmtCallBackThread.class);

    private KafkaStream<byte[], byte[]> stream = null;
    private LiveCmtService liveCmtService;


    public LiveCmtCallBackThread(String name, KafkaStream<byte[], byte[]> stream) {
        super(name);
        this.stream = stream;
        this.init();
    }

    public LiveCmtCallBackThread(ThreadGroup group, String name, KafkaStream<byte[], byte[]> stream) {
        super(group, name);
        this.stream = stream;
        this.init();
    }

    private void init() {
        this.liveCmtService = (LiveCmtService) SpringUtils.getBean("liveCmtService");
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
            LiveCmt o = new LiveCmt();
            o.setContent(JSON.parseObject(data.get("message")).getString("content"));
            o.setUid(data.get("uid"));
            o.setRptId(data.get("rptId"));
            String rid=data.get("rid");
            Live live=liveCmtService.findLiveByPrimaryKey(rid);
            MessageResponse rst = this.liveCmtService.insertLiveCmt(o,data.get("companyId"));
            LOGGER.info("{}", rst.getErrorMessage());
        } catch (Exception e) {
            LOGGER.error("系统出错{}", e);
        }
    }

}
