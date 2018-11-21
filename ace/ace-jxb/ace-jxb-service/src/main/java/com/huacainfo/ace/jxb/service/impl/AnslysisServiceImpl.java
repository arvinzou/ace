package com.huacainfo.ace.jxb.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.jxb.dao.report.OperationDao;
import com.huacainfo.ace.jxb.dao.report.ReportDao;
import com.huacainfo.ace.jxb.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("analysisService")
public class AnslysisServiceImpl implements AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnslysisServiceImpl.class);

    @Autowired
    private OperationDao operationDao;

    @Override
    public ListResult<Map<String, Object>> query(Map<String, Object> condition, String reportId) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
        List<Map<String, Object>> p = new ArrayList<Map<String, Object>>();
        ReportDao dao = (ReportDao) SpringUtils.getBean(reportId);
        List<Map<String, Object>> list = dao.query(condition);
        for (Map<String, Object> e : list) {
            p.add(CommonUtils.sortMapByKey(e));
        }
        rst.setValue(p);
        return rst;
    }

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
    @Override
    public ResultResponse operationData() {

        ReportDao dao = (ReportDao) SpringUtils.getBean("operationData");
        List<Map<String, Object>> list = dao.query(null);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", list.get(0));
    }

    /**
     * 本周营收情况
     */
    @Override
    public ResultResponse weekOperationChart() {
        Map<String, String> weekTimeMap = getWeekDateTime(DateUtil.getNowDate());
        //具体每天的营收情况
        Map<String, Map<String, Object>> detailData = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : weekTimeMap.entrySet()) {
            detailData.put(entry.getKey(), operationDao.dayOperation(entry.getValue()));
        }
        //一周总营收
        Map<String, Object> weekData = operationDao.weekOperation();
        //返回
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("weekData", weekData);
        rtnMap.put("weekTimeMap", weekTimeMap);
        rtnMap.put("detailData", detailData);
        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rtnMap);
    }

    /**
     * 年度注册用户增长趋势图（最新12个月，按月份：注册用户、付费用户）；
     *
     * @param year 年度值：如 2018
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse yearRegUserChart(String year) {

        //月度详细数据
        String[] months = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Map<String, Object> monthData = new LinkedHashMap<>();
        String dateTime;
        for (String month : months) {
            dateTime = year + "-" + month;
            monthData.put(dateTime, operationDao.userData(dateTime));
        }

        //返回数据
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("year", operationDao.userData(year));//年度总数据
        rtnMap.put("monthData", monthData);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rtnMap);
    }

    /**
     * 年度营收趋势图（最近12个月，按月度：订单总数、营收总额）。
     *
     * @param year 年度值：如 2018
     * @return ResultResponse
     * @throws Exception
     */
    @Override
    public ResultResponse yearTurnOverChart(String year) {
        //月度详细数据
        String[] months = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Map<String, Object> monthData = new LinkedHashMap<>();
        String dateTime;
        for (String month : months) {
            dateTime = year + "-" + month;
            monthData.put(dateTime, operationDao.turnoverData(dateTime));
        }

        //返回数据
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("year", operationDao.turnoverData(year));//年度总数据
        rtnMap.put("monthData", monthData);

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rtnMap);
    }


    /**
     * 获取指定日期，所在一周内的日期时间
     *
     * @return Map<String, String>
     */
    private Map<String, String> getWeekDateTime(Date date) {
        String interval = DateUtil.getTimeWeekInterval(date, null);
        String[] intervalArr = interval.split(",");
        String mondayDate = intervalArr[0];
        String month = mondayDate.substring(0, 8);
        String day = mondayDate.substring(8, 10);
        String sundayDate = intervalArr[1];

        Map<String, String> weekMap = new LinkedHashMap<>();
        weekMap.put("Monday", mondayDate);

        int dayInt = Integer.valueOf(day);
        String week = "";
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    week = "Tuesday";
                    break;
                case 1:
                    week = "Wednesday";
                    break;
                case 2:
                    week = "Thursday";
                    break;
                case 3:
                    week = "Friday";
                    break;
                case 4:
                    week = "Saturday";
                    break;
            }
            dayInt++;
            weekMap.put(week, month + parseDay(dayInt));
        }
        weekMap.put("Sunday", sundayDate);

        return weekMap;
    }

    private String parseDay(int day) {
        String str = String.valueOf(day);
        if (StringUtil.isEmpty(str) || str.length() == 2) {
            return str;
        }

        return "0" + str;
    }

}
