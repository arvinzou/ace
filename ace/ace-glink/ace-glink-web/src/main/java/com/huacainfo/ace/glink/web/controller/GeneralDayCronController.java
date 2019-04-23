package com.huacainfo.ace.glink.web.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.glink.api.SeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.fe.YearCron;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Map;

@Controller
@RequestMapping("/generalDayCron")
/**
 * @author: heshuang
 * @version: 2019-04-22
 * @Description: TODO(当天总控排程设置)
 *  */
public class GeneralDayCronController extends GLinkBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 同步数据
     *
     * @throws Exception
     */
    @RequestMapping(value = "/syncData")
    @ResponseBody
    public String syncData() throws Exception {

        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        YearCron cron = SeApiToolKit.getGeneralCtrlCron();
        String st;
        String a = "";
        switch (month) {
            case 1:
                st = cron.getM1();
                a = st.substring(day - 1, day);
                break;
            case 2:
                st = cron.getM2();
                a = st.substring(day - 1, day);
            case 3:
                st = cron.getM3();
                a = st.substring(day - 1, day);
                break;
            case 4:
                st = cron.getM4();
                a = st.substring(day - 1, day);
                break;
            case 5:
                st = cron.getM5();
                a = st.substring(day - 1, day);
                break;
            case 6:
                st = cron.getM6();
                a = st.substring(day - 1, day);
                break;
            case 7:
                st = cron.getM7();
                a = st.substring(day - 1, day);
                break;
            case 8:
                st = cron.getM9();
                a = st.substring(day - 1, day);
                break;
            case 9:
                st = cron.getM9();
                a = st.substring(day - 1, day);
                break;
            case 10:
                st = cron.getM10();
                a = st.substring(day - 1, day);
                break;
            case 11:
                st = cron.getM11();
                a = st.substring(day - 1, day);
                break;
            case 12:
                st = cron.getM12();
                a = st.substring(day - 1, day);
                break;
            default:
                break;
        }
        return a;
    }

    /**
     * 修改当天
     *
     * @param WorkMode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateDayCron")
    @ResponseBody
    public Map<String, Object> updateDayCron(int WorkMode) throws Exception {
        Map<String, Object> o = SeApiToolKit.updateDayCron(WorkMode);
        return o;
    }
}
