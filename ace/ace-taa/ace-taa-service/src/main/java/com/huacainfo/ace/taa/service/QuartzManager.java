package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.taa.constant.CommConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
    private TraAccService traAccService;

    /**
     * 每月1号中午12点开始推送，推送上一月报告给相应路长以及分路长；
     */
//    @Scheduled(cron = "0 0 12 1 * ?") //每月1号中午12点
    @Scheduled(cron = "0 0/2 * * * ?")//for debug
    public void autoSmsNotice() {
        //当前时间往上推一个月
        Date nowDateTime = DateUtil.getNowDate();
        Calendar c = Calendar.getInstance();
        c.setTime(nowDateTime);
        c.add(Calendar.MONTH, -1);
        nowDateTime = c.getTime();
        //统计数据
        List<Map<String, Object>> list = traAccService.findMothReportList(DateUtil.toStr(nowDateTime, "yyyy-MM"));
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        //循环发送
        String name;
        String dtStr;
        String mobile;
        int traAccNum;
        int injuries;
        int deathNum;
        for (Map<String, Object> item : list) {
            name = String.valueOf(item.get("roadManName"));
            mobile = String.valueOf(item.get("mobile"));
            dtStr = DateUtil.toStr(nowDateTime, CommConstant.DATE_REGEX_YM);
            traAccNum = new BigDecimal(String.valueOf(item.get("traAccNum"))).intValue();
            injuries = new BigDecimal(String.valueOf(item.get("injuries"))).intValue();
            deathNum = new BigDecimal(String.valueOf(item.get("deathNum"))).intValue();
            try {
                ResultResponse ms = traAccService.sendMonthReportSms(mobile, name, dtStr, traAccNum, injuries, deathNum);
                logger.debug("[name=][mobile=]短信发送结果:{}", name, mobile, ms.toString());
            } catch (Exception e) {
                logger.error("[name=][mobile=]短信发送失败!:{}", name, mobile, e);
            }
        }
    }

}
