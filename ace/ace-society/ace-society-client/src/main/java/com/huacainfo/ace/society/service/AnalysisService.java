package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.result.ListResult;

import java.util.Map;

public interface AnalysisService {
    public abstract ListResult<Map<String, Object>> query(
            Map<String, Object> condition, String reportId)
            throws Exception;
}
