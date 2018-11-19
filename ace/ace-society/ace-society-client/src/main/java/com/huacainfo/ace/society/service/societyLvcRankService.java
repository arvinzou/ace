package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.result.ListResult;

import java.util.Map;

public interface societyLvcRankService {
    public abstract ListResult<Map<String, Object>> query(
            Map<String, Object> condition, String reportId)
            throws Exception;

    /**
     * 爱心币排行
     *
     * @param unionId
     * @param condition 查询条件
     * @return List<Map<String, Object>>
     */
    Map<String, Object> LvcpointsRank(String regType,String unionId,Map<String, Object> condition);
}
