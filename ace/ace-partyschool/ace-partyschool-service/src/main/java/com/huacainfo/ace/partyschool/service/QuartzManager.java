package com.huacainfo.ace.partyschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Auther: Arvin
 * @Date: 2018/11/1 14:58
 * @Description:
 */
@Component("QuartzManager")
public class QuartzManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 每隔1分钟
     */
//    @Scheduled(cron = "0 */1 * * * ?")
    public void Quartz() {
        //todo 123
    }

}
