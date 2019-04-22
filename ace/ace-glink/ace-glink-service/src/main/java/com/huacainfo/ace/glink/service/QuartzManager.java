package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.result.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Auther: Arvin
 * @Date: 2018/11/1 14:58
 * @Description: 因业务需求，新增短信推送功能；
 * 对事故进行按月推送；对有死亡情况的事故进行实时推送；
 * 推送给相应路长以及分路长；
 */
@Component("QuartzManager")
public class QuartzManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PagePortalService pagePortalService;
    @Autowired
    private LeBrokenLampService leBrokenLampService;

    /**
     * 每隔[5]分钟,调用一次弱电接口：    获取设备总数&故障设备总数
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void leAutoGetLampStatus() {
        MessageResponse ms = pagePortalService.getLampStatus();
        logger.info(ms.getErrorMessage());
    }

    /**
     * 每天凌晨[1]点,调用一次弱电接口：   获取 武汉设备故障情况
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void leAutoGetBrokenLampDetail() {
        MessageResponse ms = leBrokenLampService.getBrokenLampDetail();
        logger.info(ms.getErrorMessage());
    }


}
