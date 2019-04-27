package com.huacainfo.ace.glink.web.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.glink.api.SeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.fe.YearCron;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/generalYearCron")
/**
 * @author: heshuang
 * @version: 2019-04-22
 * @Description: TODO(总控全年排程设置)
 *  */
public class GeneralYearCronController extends GLinkBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 同步数据
     *
     * @throws Exception
     */
    @RequestMapping(value = "/syncData")
    @ResponseBody
    public SingleResult syncData() throws Exception {

        YearCron cron = SeApiToolKit.getGeneralCtrlCron();
        SingleResult<YearCron> rst = new SingleResult<>();
        rst.setValue(cron);
        return rst;
    }

    /**
     * 修改
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateGeneralCtrlCron")
    @ResponseBody
    public Map<String, Object> updateGeneralCtrlCron(String jsons) throws Exception {
        YearCron cron = JsonUtil.toObject(jsons, YearCron.class);
        Map<String, Object> o = SeApiToolKit.updateGeneralCtrlCron(cron);
        return o;
    }


}
