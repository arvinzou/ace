package com.huacainfo.ace.society.service.impl;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.RemindDateUtils;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.society.dao.report.ReportDao;
import com.huacainfo.ace.society.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("analysisService")
public class AnslysisServiceImpl implements AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnslysisServiceImpl.class);

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
     * 爱心币排行
     *
     * @param unionId
     * @param rankType  year-年度排行，month-月度排行 season-季度排行
     * @param condition 查询条件
     * @return List<Map<String, Object>>
     */
    public Map<String, Object> pointsRank(String unionId, String rankType, Map<String, Object> condition) {
        ReportDao dao = (ReportDao) SpringUtils.getBean("pointsRank");
        //默认查询条件
        String nowTimeStr = DateUtil.getNow();
        String yearStr = nowTimeStr.substring(0, 4);
        String monthStr = nowTimeStr.substring(0, 7);
        switch (rankType) {
            case "year":
                condition.put("createDate", yearStr);
                break;
            case "month":
                condition.put("createDate", monthStr);
                break;
            case "season":
                String startDate =
                        DateUtil.toStr(RemindDateUtils.getCurrentSeasonStartTime(), DateUtil.DEFAULT_DATE_TIME_REGEX);
                String endDate =
                        DateUtil.toStr(RemindDateUtils.getCurrentSeasonEndTime(), DateUtil.DEFAULT_DATE_TIME_REGEX);
                condition.put("startDate", startDate);
                condition.put("endDate", endDate);
                break;
            default:
                break;
        }

        //所有排行
        List<Map<String, Object>> rankList = dao.query(condition);


        //返回结果
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("list", rankList);
        rtnMap.put("my", null);
        return rtnMap;
    }


}
