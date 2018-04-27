package com.huacainfo.ace.woc.web.controller;

import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.woc.service.AnalysisService;
import com.huacainfo.ace.woc.service.TrafficService;
import com.huacainfo.ace.woc.vo.SiteQVo;
import com.huacainfo.ace.woc.vo.SiteVo;
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

    @Autowired
    private TrafficService trafficService;

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
     * @param siteId  站点ID
     * @param startDt 查询开始时间 -- yyyy-mm-dd hh:mm:ss
     * @param endDt   查询结束时间 -- yyyy-mm-dd hh:mm:ss
     * @return map
     */
    @GetMapping("/site")
    public Map<String, Object> siteReport(String siteId, String startDt, String endDt) {
        return analysisService.siteReport(siteId, startDt, endDt, this.getCurUserProp());
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
     * 时段超载情况
     *
     * @param datetime 查询时间  -- yyyy-MM-dd hh:mm:ss
     * @param siteId   站点ID  -- woc.site.id
     * @return map
     */
    @GetMapping("/dayInterval")
    public Map<String, Object> dayIntervalReport(String datetime, String siteId) throws Exception {
        return analysisService.pcDayReport(datetime, siteId, this.getCurUserProp());
    }

    /**
     * 根据 车牌/时间，查询相关超载情况
     *
     * @param siteId  站点ID
     * @param plateNo 车牌号 可为空
     * @param startDt 查询开始时间  可为空 -- yyyy-mm-dd hh:mm:ss
     * @param endDt   查询结束时间 可为空  -- yyyy-mm-dd hh:mm:ss
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
        if (CommonUtils.isBlank(trafficId)) {
            return null;
        }

        return analysisService.illegalTrafficOne(trafficId, this.getCurWxUser(), this.getCurUserProp());
    }

    /**
     * 所有站点列表
     *
     * @param condition 查询调节，可选
     * @param start     页码 必填
     * @param limit     页数 必填
     * @return PageResult<SiteVo>
     * @throws Exception
     */
    @GetMapping("/allSite")
    public PageResult<SiteVo> allSite(SiteQVo condition, int start, int limit) throws Exception {
        return analysisService.allSite(condition, start, limit);
    }




//    @GetMapping("/test")
//    public MessageResponse test() throws Exception {
//        String nowDateTime = DateUtil.getNow();
//        String ymd = nowDateTime.substring(0, 11);
//        String startDt = ymd + "00:00:00";
//        String endDt = ymd + "23:59:59";
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("overRate", "0.3");
//        params.put("startDt", startDt);
//        params.put("endDt", endDt);
//        params.put("status", new String[]{"1"});
//        UserProp userProp = new UserProp();
//        userProp.setName("system");
//        userProp.setUserId("88888888");
//
//        return trafficService.buildIllegalTrafficData(params, userProp);
//    }
}
