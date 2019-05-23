package com.huacainfo.ace.glink.service.quartz;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.glink.constant.CommConstant;
import com.huacainfo.ace.glink.service.LeBrokenLampService;
import com.huacainfo.ace.glink.service.LeLampStatusService;
import com.huacainfo.ace.glink.service.TopBuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    private LeBrokenLampService leBrokenLampService;
    @Autowired
    private LeLampStatusService leLampStatusService;
    @Autowired
    private TopBuildingService topBuildingService;

    /**
     * 每隔[10分钟,调用一次弱电接口：    获取设备总数&故障设备总数
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void leAutoGetLampStatus() {
        MessageResponse ms = leLampStatusService.syncData();
        logger.info(ms.getErrorMessage());
    }

    /**
     * 每天凌晨[1]点,调用一次弱电接口：   获取 武汉设备故障情况
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void leAutoGetBrokenLampDetail() {
        //获取前一天数据
        Date yesterday = DateUtil.getDateByDay(DateUtil.getNowDate(), -1);
        String date = DateUtil.toStr(yesterday, CommConstant.DATE_REGEX_LE);
        //
        MessageResponse ms = leBrokenLampService.getBrokenLampDetail(date);
        logger.info(ms.getErrorMessage());
    }

    // pagePortalService.getLampStatus();

    /**
     * 每隔[1min] 刷新获取楼宇状态信息
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void leRefreshBuildingInfo() {
        UserProp system = new UserProp();
        system.setUserId("system");
        system.setName("system");
        MessageResponse ms = topBuildingService.syncData(system);
        // pagePortalService.getLampStatus();
        logger.info(ms.getErrorMessage());
    }
}
