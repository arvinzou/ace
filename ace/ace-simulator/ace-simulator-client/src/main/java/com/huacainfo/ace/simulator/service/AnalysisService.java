package com.huacainfo.ace.simulator.service;

import com.huacainfo.ace.common.result.ListResult;

import java.util.Map;

/**
 * @ClassName AnalysisService
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/8 17:40
 */
public interface AnalysisService {


    ListResult<Map<String, Object>> query(Map<String, Object> condition, String reportId) throws Exception;
}

