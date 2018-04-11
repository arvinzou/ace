package com.huacainfo.ace.woc.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;

import java.util.Map;

public interface AnalysisService {
    public abstract ListResult<Map<String, Object>> query(
            Map<String, Object> condition, String reportId, int start, int limit)
            throws Exception;

    /**
     * 查询 以下累计数据
     * 1.今日通行记录数
     * 2.今日违章记录数
     * 3.待审通行记录数
     * 4.待审案件数
     *
     * @return
     */
    Map<String,Object> queryCounts(UserProp curUserProp);

    /**
     * 今日通行记录折线图
     * @param curUser
     * @return
     */
    Map<String,Object> todayChart(UserProp curUser);
}
