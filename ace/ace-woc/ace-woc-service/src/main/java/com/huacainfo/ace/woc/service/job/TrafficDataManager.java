package com.huacainfo.ace.woc.service.job;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.woc.model.Vehicle;
import com.huacainfo.ace.woc.service.TrafficService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by HuaCai008 on 2018/4/11.
 */
@Component("TrafficJob")
public class TrafficDataManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TrafficService trafficService;

    public static final String[] DIRECTION = new String[]{"由南至北", "由北至南", "由东向西", "由西向东"};

    public static final int[] OVER_RATE = new int[]{0, 10, 20, 30};

    public static int getRandomRate() {
        Random r = new Random();
        return OVER_RATE[r.nextInt(OVER_RATE.length)];
    }

    public static BigDecimal getCheckTotalMass(int rate, Vehicle v) {

        BigDecimal checkedData = BigDecimal.ZERO;
        switch (rate) {
            case 0:
                return v.getTotalMass();
            case 10:
                return v.getTotalMass()
                        .add(v.getTotalMass().multiply(new BigDecimal(0.1)))
                        .add(new BigDecimal(100));
            case 20:
                return v.getTotalMass()
                        .add(v.getTotalMass().multiply(new BigDecimal(0.2)))
                        .add(new BigDecimal(300));
            case 30:
                return v.getTotalMass()
                        .add(v.getTotalMass().multiply(new BigDecimal(0.3)))
                        .add(new BigDecimal(500));
            default:
                return BigDecimal.ZERO;
        }
    }


    /**
     * 每1分钟产生一次通行记录
     */
    @Scheduled(cron = " 0 0/1 * * * ?")
    public void buildTrafficData() {
        try {
            UserProp userProp = new UserProp();
            userProp.setName("system");
            userProp.setUserId("88888888");

            trafficService.createData(DateUtil.getNow(), "1", userProp);
        } catch (Exception e) {
            logger.error("TrafficDataManager.createData.error:{}", e);
        }
    }


    /**
     * 每30分钟,对已产生的通行记录，进行确认违章处理
     */
    @Scheduled(cron = " 0 0/30 * * * ?")
    public void buildIllegalTrafficData() {
        try {
            String nowDateTime = DateUtil.getNow();
            String ymd = nowDateTime.substring(0,11);
            String startDt = ymd + "00:00:00";
            String endDt = ymd + "23:59:59";

            Map<String, Object> params = new HashMap<>();
            params.put("overRate", 0.3);
            params.put("status", new String[]{"1"});
            params.put("startDt", startDt);
            params.put("endDt", endDt);
            UserProp userProp = new UserProp();
            userProp.setName("system");
            userProp.setUserId("88888888");

            trafficService.buildIllegalTrafficData(params, userProp);
        } catch (Exception e) {
            logger.error("TrafficDataManager.buildIllegalTrafficData.error:{}", e);
        }
    }
}
