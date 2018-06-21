package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.result.ListResult;

import java.util.List;
import java.util.Map;

public interface AnalysisService {
    ListResult<Map<String, Object>> query(
            Map<String, Object> condition, String reportId, int start, int limit) throws Exception;

    /**
     * 财务公开数额统计
     *
     * @return
     */
    Map<String, Object> financeStatistics();

    /**
     * 慈善榜单
     *
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    List<Map<String, Object>> donateRank(int start, int limit, String orderBy);
}
