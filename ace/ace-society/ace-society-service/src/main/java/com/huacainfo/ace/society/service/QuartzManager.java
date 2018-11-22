package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.service.WeChatApiService;
import com.huacainfo.ace.society.dao.ActivityDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//import com.huacainfo.ace.portal.service.WeChatApiService;

/**
 * @Auther: Arvin
 * @Date: 2018/11/1 14:58
 * @Description:
 */
@Component("QuartzManager")
public class QuartzManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private WeChatApiService weChatApiService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    /**
     * 每隔1分钟 自动关闭一次 超时的活动数据
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void autoCloseActivity() {
        try {
            int count = activityDao.autoCloseTimeOutData();
            logger.info("[" + DateUtil.getNow() + "]自动关闭超时活动【" + count + "】条");
        } catch (Exception e) {
            logger.error("[" + DateUtil.getNow() + "]自动关闭超时活动发送异常：\n{}", e);
        }
    }


    /**
     * 拉取公众号已关注用户列表  每天pm 23：30 执行一次
     */
    @Scheduled(cron = "0 30 23 * * ?")
    public void sysUserList() throws Exception {
        logger.debug("[" + DateUtil.getNow() + "]拉取公众号已关注用户列表--开始-----");
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("sysId", "society");
            data.put("service", "weChatApiService");
            kafkaProducerService.sendMsg("topic.sys.msg", data);
           // ResultResponse rs = weChatApiService.synUserList("society");
//            logger.debug("[" + DateUtil.getNow() + "]拉取公众号已关注用户列表：{}", JsonUtil.toJson(rs));
        } catch (Exception e) {
            logger.error("[" + DateUtil.getNow() + "]拉取公众号已关注用户列表：\n{}", e);
        }
    }
}
