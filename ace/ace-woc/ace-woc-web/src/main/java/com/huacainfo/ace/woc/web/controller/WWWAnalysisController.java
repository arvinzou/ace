package com.huacainfo.ace.woc.web.controller;

import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.woc.service.AnalysisService;
import com.huacainfo.ace.woc.vo.TrafficVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by HuaCai008 on 2018/4/16.
 */
@RestController
@RequestMapping("/www/data")
public class WWWAnalysisController extends WocBaseController {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AnalysisService analysisService;


    /**
     * 手机端数据统计
     * <p>
     * 查询 以下累计数据
     * 1.今日通行记录数，及检测数  - trafficCounts
     * 2.今日违章记录数，及超载次数 - trafficIllegalCounts
     * 3.待审通行记录数            - ptTrafficCounts
     * 4.待审案件数，及案件数       - ptCasesCounts
     *
     * @param datetime 查询时间  -- yyyy-MM-dd hh:mm:ss
     * @param siteId   站点ID  -- woc.site.id
     * @return map
     */
    @GetMapping("/statistics")
    public Map<String, Object> statistics(String siteId, String datetime) {
        return analysisService.queryCounts(siteId, datetime, this.getCurUserProp());
    }


    /**
     * 站点超载情况
     *
     * @param startDt 查询开始时间
     * @param endDt   查询结束时间
     * @return map
     */
    @GetMapping("/site")
    public Map<String, Object> siteReport(String startDt, String endDt) {
        return analysisService.siteReport(startDt, endDt, this.getCurUserProp());
    }


    /**
     * 时段超载情况
     *
     * @param datetime 查询时间  -- yyyy-MM-dd hh:mm:ss
     * @param siteId   站点ID  -- woc.site.id
     * @return map
     */
    @GetMapping("/interval")
    public Map<String, Object> intervalReport(String datetime, String siteId) {
        return analysisService.intervalReport(datetime, siteId, this.getCurUserProp());
    }

    /**
     * 根据 车牌/时间，查询相关超载情况
     *
     * @param siteId  站点ID
     * @param plateNo 车牌号 可为空
     * @param startDt 查询开始时间  可为空
     * @param endDt   查询结束时间 可为空
     * @param start   页码，不能为空
     * @param limit   页数 不能为空
     * @return map
     */
    @GetMapping("/illegalTraffic")
    public PageResult<TrafficVo> illegalTraffic(String siteId, String plateNo, String startDt, String endDt, int start, int limit) {

        return analysisService.illegalTraffic(siteId, plateNo, startDt, endDt, start, limit,
                this.getCurWxUser(), this.getCurUserProp());
    }

    /**
     * 查询单条通行记录详情
     *
     * @param trafficId 通行记录ID
     * @return map
     */
    @GetMapping("/illegalTrafficOne")
    public SingleResult<TrafficVo> illegalTrafficOne(String trafficId) throws Exception {
        if (CommonUtils.isBlank(trafficId))
            return null;

        return analysisService.illegalTrafficOne(trafficId, this.getCurWxUser(), this.getCurUserProp());
    }
}
