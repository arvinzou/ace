package com.huacainfo.ace.woc.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.vo.SiteQVo;
import com.huacainfo.ace.woc.vo.SiteVo;
import com.huacainfo.ace.woc.vo.TrafficVo;

import java.util.Map;

public interface AnalysisService {
    public abstract ListResult<Map<String, Object>> query(
            Map<String, Object> condition, String reportId, int start, int limit)
            throws Exception;

    /**
     * 查询 以下累计数据
     * 1.今日通行记录数，及检测数  - trafficCounts
     * 2.今日违章记录数，及超载次数 - trafficIllegalCounts
     * 3.待审通行记录数            - ptTrafficCounts
     * 4.待审案件数，及案件数       - ptCasesCounts
     *
     * @param siteId   站点ID
     * @param datetime 查询时间
     * @return
     */
    Map<String, Object> queryCounts(String siteId, String datetime, UserProp curUserProp);


    Map<String, Object> caseCounts(String siteId, String startDt, String endDt,  UserProp curUserProp);

    /**
     * 今日通行记录折线图
     *
     * @param curUser
     * @return
     */
    Map<String, Object> todayChart(UserProp curUser);

    /**
     * 站点超载情况
     *
     *
     * @param siteId
     * @param startDt 查询开始时间
     * @param endDt   查询结束时间
     * @return map
     */
    Map<String, Object> siteReport(String siteId, String startDt, String endDt, UserProp curUserProp);

    /**
     * 时段超载情况
     *
     * @param datetime 查询时间  -- yyyy-MM-dd hh:mm:ss
     * @param siteId   站点ID  -- woc.site.id
     * @return map
     */
    Map<String, Object> intervalReport(String datetime, String siteId, UserProp curUserProp);


    /**
     * 根据 车牌/时间，查询相关超载情况
     *
     * @param siteId      站点ID
     * @param plateNo     车牌号 可为空
     * @param startDt     查询开始时间  可为空
     * @param endDt       查询结束时间 可为空
     * @param start       页码，不能为空
     * @param limit       页数 不能为空
     * @param curWxUser   当前微信用户信息
     * @param curUserProp 当前登录用户信息   @return map
     */
    PageResult<TrafficVo> illegalTraffic(String siteId, String plateNo, String startDt, String endDt, int start, int limit,
                                         WxUser curWxUser, UserProp curUserProp);

    /**
     * 查询单条通行记录详情
     *
     * @param trafficId 通行记录ID
     * @return map
     */
    SingleResult<TrafficVo> illegalTrafficOne(String trafficId, WxUser curWxUser, UserProp curUserProp) throws Exception;

    /**
     * 所有站点列表
     *
     * @param condition 查询调节，可选
     * @param start     页码 必填
     * @param limit     页数 必填
     * @return PageResult<SiteVo>
     * @throws Exception
     */
    PageResult<SiteVo> allSite(SiteQVo condition, int start, int limit) throws Exception;

    Map<String, Object> pcDayReport(String datetime, String siteId, UserProp curUser) throws Exception;
}
