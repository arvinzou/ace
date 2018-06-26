package com.huacainfo.ace.cu.service.impl;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.cu.dao.report.ReportDao;
import com.huacainfo.ace.cu.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("analysisService")
public class AnslysisServiceImpl implements AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnslysisServiceImpl.class);

    @Override
    public ListResult<Map<String, Object>> query(Map<String, Object> condition,
                                                 String reportId, int start, int limit) throws Exception {
//		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
//		List<Map<String, Object>> p=new ArrayList<Map<String, Object>>();
//		ReportDao dao = (ReportDao) SpringUtils.getBean(reportId);
//		condition.put("start",start);
//		condition.put("limit",limit);
//		List<Map<String, Object>> list=dao.query(condition);
//		for(Map<String, Object> e:list){
//			p.add(CommonUtils.sortMapByKey(e));
//		}
//		rst.setValue(p);
//		return rst;
        return null;
    }

    /**
     * 财务公开数额统计
     *
     * @param projectId
     * @returnf
     */
    @Override
    public Map<String, Object> financeStatistics(String projectId) {
        ReportDao dao = (ReportDao) SpringUtils.getBean("financeStatistics");

        Map<String, Object> condition = new HashMap<>();
        if (!CommonUtils.isEmpty(projectId)) {
            condition.put("ids", projectId.split(","));
        }

        List<Map<String, Object>> mapList = dao.query(condition);

        return CollectionUtils.isEmpty(mapList) ? null : mapList.get(0);
    }

    /**
     * 慈善榜单
     *
     * @param projectId
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    @Override
    public List<Map<String, Object>> donateRank(String projectId, int start, int limit, String orderBy) {
        ReportDao dao = (ReportDao) SpringUtils.getBean("donateRank");

        Map<String, Object> condition = new HashMap<>();
        condition.put("projectId", projectId);
        condition.put("start", start);
        condition.put("limit", limit);
        condition.put("orderBy", orderBy);
        condition.put("appid", PropertyUtil.getProperty("appid"));
        List<Map<String, Object>> mapList = dao.query(condition);
        return mapList;
    }


}
