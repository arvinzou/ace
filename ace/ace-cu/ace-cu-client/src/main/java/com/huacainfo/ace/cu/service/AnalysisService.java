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
     * @param projectId
     */
    Map<String, Object> financeStatistics(String projectId);

    /**
     * 慈善榜单
     *
     * @param projectId 项目ID
     * @param openId    微信openid
     * @param start     页码
     * @param limit     页数
     * @param orderBy
     * @return
     */
    List<Map<String, Object>> donateRank(String projectId, String openId, int start, int limit, String orderBy);

    /**
     * 慈善榜单
     */
    Map<String, Object> donateRank(String projectId, String needOpenId, String openId,
                                   int start, int limit, String orderBy);

    Map<String, Object> findDonateRecord(String projectId, String openId);

    Map<String, Object> findDonateHis(String orderId) throws Exception;
}
