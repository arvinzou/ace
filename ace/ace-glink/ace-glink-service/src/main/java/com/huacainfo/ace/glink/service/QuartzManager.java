package com.huacainfo.ace.glink.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


}
