package com.huacainfo.ace.woc.service.impl;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.woc.dao.CasesDao;
import com.huacainfo.ace.woc.dao.TrafficDao;
import com.huacainfo.ace.woc.dao.TrafficIllegalDao;
import com.huacainfo.ace.woc.dao.report.ReportDao;
import com.huacainfo.ace.woc.service.AnalysisService;
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

    @Autowired
    private CasesDao casesDao;

    @Autowired
    private TrafficDao trafficDao;

    @Autowired
    private TrafficIllegalDao trafficIllegalDao;

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
     * 1.今日通行记录数
     * 2.今日违章记录数
     * 3.待审通行记录数
     * 4.待审案件数
     *
     * @param curUser
     * @return
     */
    @Override
    public Map<String, Object> queryCounts(UserProp curUser) {
        String nowDate = DateUtil.getNow();
        Map<String, Object> params = new HashMap<>();
        params.put("nowDate", DateUtil.getNow());
        //今日通行记录数
        Map<String, Object> result = trafficDao.getStatisticsCounts(params);

        Map<String, Object> rtnData = new HashMap<>();
        rtnData.put("trafficCounts", result.get("trafficCounts"));
        rtnData.put("trafficIllegalCounts", result.get("trafficIllegalCounts"));
        rtnData.put("ptTrafficCounts", result.get("ptTrafficCounts"));
        rtnData.put("ptCasesCounts", result.get("ptCasesCounts"));

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
}
