package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.result.ListResult;

import java.util.Map;

public interface AnalysisService {
    ListResult<Map<String, Object>> query(Map<String, Object> condition, String reportId,
                                          int start, int limit) throws Exception;

    /**
     * portal页顶端数据统计
     *
     * @return
     */
    Map<String, Object> portalCount() throws Exception;
}
