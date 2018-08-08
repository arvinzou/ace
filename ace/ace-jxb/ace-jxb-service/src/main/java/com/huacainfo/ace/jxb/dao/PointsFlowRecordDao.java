package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.PointsFlowRecord;

public interface PointsFlowRecordDao {
    int deleteByPrimaryKey(String id);

    int insert(PointsFlowRecord record);

    int insertSelective(PointsFlowRecord record);

    PointsFlowRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PointsFlowRecord record);

    int updateByPrimaryKey(PointsFlowRecord record);
}