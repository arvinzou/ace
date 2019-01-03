package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.result.ListResult;

import java.util.Map;

public interface AnalysisService {
    ListResult<Map<String, Object>> query(
            Map<String, Object> condition, String reportId)
            throws Exception;
}
