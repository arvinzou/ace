package com.huacainfo.ace.iop.service;

import java.util.Map;

import com.huacainfo.ace.common.result.ListResult;

public interface AnalysisService {
    public abstract ListResult<Map<String, Object>> query(
            Map<String, Object> condition, String reportId, int start, int limit)
            throws Exception;
}
