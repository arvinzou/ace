package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.result.ListResult;

import java.util.Map;

public interface AnalysisService {
    public abstract ListResult<Map<String, Object>> query(
            Map<String, Object> condition, String reportId)
            throws Exception;

    /**
     * 爱心币排行
     *
     * @param unionId
     * @param rankType
     * @param condition 查询条件
     * @return List<Map<String, Object>>
     */
    Map<String, Object> pointsRank(String unionId, String rankType, Map<String, Object> condition);
}
