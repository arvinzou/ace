package com.huacainfo.ace.glink.service.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Auther: Arvin
 * @Date: 2018/11/1 14:58
 * @Description: 其他调度任务管理
 */
@Component("quartzManager")
public class QuartzManager {


    /**
     * 每隔[5]分钟,调用一次强电接口：    同步配电箱监测数据
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void autoSmsNotice() {
//        MessageResponse ms = seNodeService.syncMonitorData(null);
//        logger.debug("[自动同步配电箱监测数据]=>{}", ms.getErrorMessage());
    }

}
