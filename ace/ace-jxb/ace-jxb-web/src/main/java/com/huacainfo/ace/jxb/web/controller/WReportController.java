package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.jxb.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/9/6 10:23
 * @Description:
 */
@RestController
@RequestMapping("/www/report")
public class WReportController extends JxbBaseController {

    Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    @Autowired
    private AnalysisService analysisService;

    /**
     * 经营数据
     *
     * @return Map<String,Object>
     * regUserCount -- 注册用户总数(包含咨询师)
     * paidUserCount -- 付费用户
     * todayRegUserCount -- 注册用户（今日新增）
     * regTeacherCount  -- 咨询师(只含审核通过的)
     * todayRegTeacherCount -- 咨询师（今日新增）
     * yearTurnover  -- 年度营收
     * todayTurnover    -- 今日营收
     * monthTurnover    -- 本月营收
     * @throws Exception
     */
    @RequestMapping(value = "/operationData")
    public ResultResponse operationData() throws Exception {

        return analysisService.operationData();
    }

    /**
     * 本周营收（本周，按天：订单总数、营收总额）
     *
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping(value = "/weekOperationChart")
    public ResultResponse weekOperationChart() throws Exception {

        return analysisService.weekOperationChart();
    }

    /**
     * 年度注册用户增长趋势图（最新12个月，按月份：注册用户、付费用户）；
     *
     * @param year 年度值：如 2018
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping(value = "/yearRegUserChart")
    public ResultResponse weekOperation(String year) throws Exception {
        if (StringUtil.isEmpty(year)) {
            year = DateUtil.getNow().substring(0, 4);
        }
        return analysisService.yearRegUserChart(year);
    }

    /**
     * 年度营收趋势图（最近12个月，按月度：订单总数、营收总额）。
     *
     * @param year 年度值：如 2018
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping(value = "/yearTurnOverChart")
    public ResultResponse yearTurnOverChart(String year) throws Exception {
        if (StringUtil.isEmpty(year)) {
            year = DateUtil.getNow().substring(0, 4);
        }
        return analysisService.yearTurnOverChart(year);
    }
}
