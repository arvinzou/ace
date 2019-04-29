package com.huacainfo.ace.glink.service.quartz;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.glink.service.PagePortalService;
import com.huacainfo.ace.glink.service.SeNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName SeQuartzManager
 * @Description 强电调度任务处理
 * @Author Arvin Zou
 * @Date 2019/4/22 18:14
 */
@Component("seQuartzManager")
public class SeQuartzManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeNodeService seNodeService;
    @Autowired
    private PagePortalService pagePortalService;


    /**
     * 每隔[5]分钟,调用一次强电接口：    同步配电箱监测数据
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void autoSyncMonitorData() {
        MessageResponse ms = seNodeService.syncMonitorData(null);
        logger.debug("[自动同步配电箱监测数据]=>{}", ms.getErrorMessage());
    }

    /**
     * 每隔[5]分钟,调用一次强电接口：    同步配电箱全部电表数据
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void autoSyncNodeMeterData() {
        //同步基础数据
        MessageResponse ms = seNodeService.syncNodeMeterData(null);
//        logger.debug(ms.getErrorMessage());
//        //portal数据统计
//        ms = pagePortalService.autoSyncNodeMeterData();
        logger.debug("[自动同步配电箱全部电表数据]=>{}", ms.getErrorMessage());
    }

    /**
     * 每隔[6]分钟,自动统计数据
     */
    @Scheduled(cron = "0 0/6 * * * ?")
    public void autoDataStatistics() {
        //同步基础数据
        MessageResponse ms = pagePortalService.autoDataStatistics();
        logger.debug("[自动统计数据]=>{}", ms.getErrorMessage());
    }
}
