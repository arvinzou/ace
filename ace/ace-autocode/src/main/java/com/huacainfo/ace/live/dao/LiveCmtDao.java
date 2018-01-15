package com.huacainfo.ace.live.dao;

import com.huacainfo.ace.live.model.LiveCmt;

public interface LiveCmtDao {
    int deleteByPrimaryKey(String id);

    int insert(LiveCmt record);

    int insertSelective(LiveCmt record);

    LiveCmt selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LiveCmt record);

    int updateByPrimaryKeyWithBLOBs(LiveCmt record);

    int updateByPrimaryKey(LiveCmt record);
}