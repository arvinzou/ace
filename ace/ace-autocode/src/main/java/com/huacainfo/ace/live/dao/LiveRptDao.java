package com.huacainfo.ace.live.dao;

import com.huacainfo.ace.live.model.LiveRpt;
import com.huacainfo.ace.live.model.LiveRptWithBLOBs;

public interface LiveRptDao {
    int deleteByPrimaryKey(String id);

    int insert(LiveRptWithBLOBs record);

    int insertSelective(LiveRptWithBLOBs record);

    LiveRptWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LiveRptWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LiveRptWithBLOBs record);

    int updateByPrimaryKey(LiveRpt record);
}