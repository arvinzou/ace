package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.ResultResponse;

import java.util.Map;

public interface AnalysisService {
    public abstract ListResult<Map<String, Object>> query(
            Map<String, Object> condition, String reportId)
            throws Exception;

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
    ResultResponse operationData();

    /**
     * 本周营收情况
     */
    ResultResponse weekOperationChart();


    /**
     * 年度注册用户增长趋势图（最新12个月，按月份：注册用户、付费用户）；
     *
     * @param year 年度值：如 2018
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse yearRegUserChart(String year);

    /**
     * 年度营收趋势图（最近12个月，按月度：订单总数、营收总额）。
     *
     * @param year 年度值：如 2018
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse yearTurnOverChart(String year);
}
