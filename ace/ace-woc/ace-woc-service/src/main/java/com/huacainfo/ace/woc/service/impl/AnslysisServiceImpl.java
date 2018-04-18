package com.huacainfo.ace.woc.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.woc.dao.SiteDao;
import com.huacainfo.ace.woc.dao.TrafficDao;
import com.huacainfo.ace.woc.dao.report.ReportDao;
import com.huacainfo.ace.woc.model.Site;
import com.huacainfo.ace.woc.service.AnalysisService;
import com.huacainfo.ace.woc.service.SiteService;
import com.huacainfo.ace.woc.service.TrafficService;
import com.huacainfo.ace.woc.vo.SiteQVo;
import com.huacainfo.ace.woc.vo.SiteVo;
import com.huacainfo.ace.woc.vo.TrafficVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("analysisService")
public class AnslysisServiceImpl implements AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnslysisServiceImpl.class);

    public static final String[] PC_INTERVAL = new String[]{"0~1", "1~2", "2~3", "3~4", "4~5", "5~6", "6-7", "7~8", "8~9", "9~10",
            "10~11", "11~12", "12~13", "13~14", "14~15", "15-16", "16~17", "17~18", "18~19", "19~20", "20~21", "21~22", "22~23", "23~24"};

    public static final String[] MOBILE_INTERVAL = new String[]{"0~4", "4~8", "8~12", "12~16", "16~20", "20~24"};

    @Autowired
    private SiteService siteService;
    @Autowired
    private TrafficService trafficService;
    @Autowired
    private SiteDao siteDao;
    @Autowired
    private TrafficDao trafficDao;


    @Override
    public ListResult<Map<String, Object>> query(Map<String, Object> condition,
                                                 String reportId, int start, int limit) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
        List<Map<String, Object>> p = new ArrayList<Map<String, Object>>();
        ReportDao dao = (ReportDao) SpringUtils.getBean(reportId);
        condition.put("start", start);
        condition.put("limit", limit);
        List<Map<String, Object>> list = dao.query(condition);
        for (Map<String, Object> e : list) {
            p.add(CommonUtils.sortMapByKey(e));
        }
        rst.setValue(p);
        return rst;
    }

    /**
     * 查询 以下累计数据
     * 1.今日通行记录数，及检测数  - trafficCounts
     * 2.今日违章记录数，及超载次数 - trafficIllegalCounts
     * 3.待审通行记录数            - ptTrafficCounts
     * 4.待审案件数，及案件数       - ptCasesCounts
     *
     * @param siteId
     * @param curUser
     * @return
     */
    @Override
    public Map<String, Object> queryCounts(String siteId, String datetime, UserProp curUser) {
        String nowDate = CommonUtils.isBlank(datetime) ? DateUtil.getNow() : datetime;
        Map<String, Object> params = new HashMap<>();
        params.put("nowDate", nowDate);
        params.put("siteId", siteId);
        //今日通行记录数
        Map<String, Object> result = trafficDao.selectStatistics(params);

        Map<String, Object> rtnData = new HashMap<>();
        rtnData.put("trafficCounts", null == result ? 0 : result.get("trafficCounts"));
        rtnData.put("trafficIllegalCounts", null == result ? 0 : result.get("trafficIllegalCounts"));
        rtnData.put("ptTrafficCounts", null == result ? 0 : result.get("ptTrafficCounts"));
        rtnData.put("ptCasesCounts", null == result ? 0 : result.get("ptCasesCounts"));

        return rtnData;
    }

    /**
     * 今日通行记录折线图
     *
     * @param curUser
     * @return
     */
    @Override
    public Map<String, Object> todayChart(UserProp curUser) {

        return null;
    }

    /**
     * 站点超载情况
     *
     * @param siteId
     * @param startDt 查询开始时间
     * @param endDt   查询结束时间
     * @param curUser 当前登录用户
     * @return map
     */
    @Override
    public Map<String, Object> siteReport(String siteId, String startDt, String endDt, UserProp curUser) {

        List<Site> siteList = siteDao.selectAll();
        Map<String, Object> condition = new HashMap<>();
        condition.put("startDt", startDt);
        condition.put("endDt", endDt);
        condition.put("status", new String[]{"0"});
        int count;
        for (Site s : siteList) {
            condition.put("siteId", s.getId());
            count = trafficDao.selectCount(condition);
            s.setCount(count);
        }

        Map<String, Object> rtn = new HashMap<>();
        rtn.put("siteList", siteList);
        return rtn;
    }

    /**
     * 时段超载情况
     *
     * @param datetime 查询时间  -- yyyy-MM-dd hh:mm:ss
     * @param siteId   站点ID  -- woc.site.id
     * @return map
     */
    @Override
    public Map<String, Object> intervalReport(String datetime, String siteId, UserProp curUser) {

        if (null != curUser) {
            return pcIntervalReport(datetime, siteId, curUser);
        } else {
            return mobileIntervalReport(datetime, siteId, curUser);
        }
    }

    private Map<String, Object> mobileIntervalReport(String datetime, String siteId, UserProp curUser) {
        datetime = CommonUtils.isBlank(datetime) ? DateUtil.getNow() : datetime;
        String ymd = datetime.substring(0, 11);
        Map<String, Integer> countMap = new HashMap<>();
        int count;
        for (String period : MOBILE_INTERVAL) {
            switch (period) {
                case "0~4":
                    count = getIllegalCount(siteId, ymd + "00:00:00", ymd + "04:00:00");
                    countMap.put(period, count);
                    continue;
                case "4~8":
                    count = getIllegalCount(siteId, ymd + "04:00:00", ymd + "08:00:00");
                    countMap.put(period, count);
                    continue;
                case "8~12":
                    count = getIllegalCount(siteId, ymd + "08:00:00", ymd + "12:00:00");
                    countMap.put(period, count);
                    continue;
                case "12~16":
                    count = getIllegalCount(siteId, ymd + "12:00:00", ymd + "16:00:00");
                    countMap.put(period, count);
                    continue;
                case "16~20":
                    count = getIllegalCount(siteId, ymd + "16:00:00", ymd + "20:00:00");
                    countMap.put(period, count);
                    continue;
                case "20~24":
                    count = getIllegalCount(siteId, ymd + "20:00:00", ymd + "24:00:00");
                    countMap.put(period, count);
                    continue;
                default:
                    continue;
            }
        }

        Map<String, Object> rtn = new HashMap<>();
        rtn.put("interval", MOBILE_INTERVAL);//横轴坐标
        rtn.put("countMap", countMap);//key - 横轴坐标值，value - y轴坐标值
        return rtn;
    }

    private Map<String, Object> pcIntervalReport(String datetime, String siteId, UserProp curUser) {
        datetime = CommonUtils.isBlank(datetime) ? DateUtil.getNow() : datetime;
        String ymd = datetime.substring(0, 11);
        Map<String, Integer> countMap = new HashMap<>();
        int count;
        for (String period : PC_INTERVAL) {
            switch (period) {
                case "0~1":
                    count = getIllegalCount(siteId, ymd + "00:00:00", ymd + "01:00:00");
                    countMap.put(period, count);
                    continue;
                case "1~2":
                    count = getIllegalCount(siteId, ymd + "01:00:00", ymd + "02:00:00");
                    countMap.put(period, count);
                    continue;
                case "2~3":
                    count = getIllegalCount(siteId, ymd + "02:00:00", ymd + "03:00:00");
                    countMap.put(period, count);
                    continue;
                case "3~4":
                    count = getIllegalCount(siteId, ymd + "03:00:00", ymd + "04:00:00");
                    countMap.put(period, count);
                    continue;
                case "4~5":
                    count = getIllegalCount(siteId, ymd + "04:00:00", ymd + "05:00:00");
                    countMap.put(period, count);
                    continue;
                case "5~6":
                    count = getIllegalCount(siteId, ymd + "05:00:00", ymd + "06:00:00");
                    countMap.put(period, count);
                    continue;
                case "6-7":
                    count = getIllegalCount(siteId, ymd + "06:00:00", ymd + "07:00:00");
                    countMap.put(period, count);
                    continue;
                case "7~8":
                    count = getIllegalCount(siteId, ymd + "07:00:00", ymd + "08:00:00");
                    countMap.put(period, count);
                    continue;
                case "8~9":
                    count = getIllegalCount(siteId, ymd + "08:00:00", ymd + "09:00:00");
                    countMap.put(period, count);
                    continue;
                case "9~10":
                    count = getIllegalCount(siteId, ymd + "09:00:00", ymd + "10:00:00");
                    countMap.put(period, count);
                    continue;
                case "10~11":
                    count = getIllegalCount(siteId, ymd + "10:00:00", ymd + "11:00:00");
                    countMap.put(period, count);
                    continue;
                case "11~12":
                    count = getIllegalCount(siteId, ymd + "11:00:00", ymd + "12:00:00");
                    countMap.put(period, count);
                    continue;
                case "12~13":
                    count = getIllegalCount(siteId, ymd + "12:00:00", ymd + "13:00:00");
                    countMap.put(period, count);
                    continue;
                case "13~14":
                    count = getIllegalCount(siteId, ymd + "13:00:00", ymd + "14:00:00");
                    countMap.put(period, count);
                    continue;
                case "14~15":
                    count = getIllegalCount(siteId, ymd + "14:00:00", ymd + "15:00:00");
                    countMap.put(period, count);
                    continue;
                case "15-16":
                    count = getIllegalCount(siteId, ymd + "15:00:00", ymd + "16:00:00");
                    countMap.put(period, count);
                    continue;
                case "16~17":
                    count = getIllegalCount(siteId, ymd + "16:00:00", ymd + "17:00:00");
                    countMap.put(period, count);
                    continue;
                case "17~18":
                    count = getIllegalCount(siteId, ymd + "17:00:00", ymd + "18:00:00");
                    countMap.put(period, count);
                    continue;
                case "18~19":
                    count = getIllegalCount(siteId, ymd + "18:00:00", ymd + "19:00:00");
                    countMap.put(period, count);
                    continue;
                case "19~20":
                    count = getIllegalCount(siteId, ymd + "19:00:00", ymd + "20:00:00");
                    countMap.put(period, count);
                    continue;
                case "20~21":
                    count = getIllegalCount(siteId, ymd + "20:00:00", ymd + "21:00:00");
                    countMap.put(period, count);
                    continue;
                case "21~22":
                    count = getIllegalCount(siteId, ymd + "21:00:00", ymd + "22:00:00");
                    countMap.put(period, count);
                    continue;
                case "22~23":
                    count = getIllegalCount(siteId, ymd + "22:00:00", ymd + "23:00:00");
                    countMap.put(period, count);
                    continue;
                case "23~24":
                    count = getIllegalCount(siteId, ymd + "23:00:00", ymd + "24:00:00");
                    countMap.put(period, count);
                    continue;
                default:
                    continue;
            }
        }


        Map<String, Object> rtn = new HashMap<>();
        rtn.put("interval", PC_INTERVAL);//横轴坐标
        rtn.put("countMap", countMap);//key - 横轴坐标值，value - y轴坐标值
        return rtn;
    }

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
    @Override
    public PageResult<TrafficVo> illegalTraffic(String siteId, String plateNo, String startDt, String endDt,
                                                int start, int limit,
                                                WxUser curWxUser, UserProp curUserProp) {

        Map<String, Object> c = new HashMap<>();
        c.put("startDt", startDt);
        c.put("endDt", endDt);
        c.put("plateNo", plateNo);
        c.put("siteId", siteId);
        c.put("status", new String[]{"0"});//违章记录状态

        List<TrafficVo> trafficList = trafficDao.selectTrafficList(c, start, limit);
        PageResult<TrafficVo> rst = new PageResult<>();
        rst.setRows(trafficList);

        return rst;
    }

    /**
     * 查询单条通行记录详情
     *
     * @param trafficId   通行记录ID
     * @param curWxUser
     * @param curUserProp @return map
     */
    @Override
    public SingleResult<TrafficVo> illegalTrafficOne(String trafficId, WxUser curWxUser,
                                                     UserProp curUserProp) throws Exception {
        return trafficService.selectTrafficByPrimaryKey(trafficId);
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
    @Override
    public PageResult<SiteVo> allSite(SiteQVo condition, int start, int limit) throws Exception {
        return siteService.findSiteList(condition, start, limit, "");
    }


    private int getIllegalCount(String siteId, String startDt, String endDt) {
        Map<String, Object> c = new HashMap<>();
        c.put("startDt", startDt);
        c.put("endDt", endDt);
        c.put("siteId", siteId);
        c.put("status", new String[]{"0"});
        return trafficDao.selectCount(c);
    }
}
