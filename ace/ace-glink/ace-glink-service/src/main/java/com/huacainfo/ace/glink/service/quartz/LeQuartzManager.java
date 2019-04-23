package com.huacainfo.ace.glink.service.quartz;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.glink.service.LeBrokenLampService;
import com.huacainfo.ace.glink.service.PagePortalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName LeQuartzManager
 * @Description 弱电调度任务管理
 * @Author Arvin Zou
 * @Date 2019/4/22 18:19
 */
@Component("leQuartzManager")
public class LeQuartzManager {

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
