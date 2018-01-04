package com.huacainfo.ace.live.dao;

import com.huacainfo.ace.live.model.LiveSub;

public interface LiveSubDao {
    int deleteByPrimaryKey(String id);

    int insert(LiveSub record);

    int insertSelective(LiveSub record);

    LiveSub selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LiveSub record);

    int updateByPrimaryKeyWithBLOBs(LiveSub record);

    int updateByPrimaryKey(LiveSub record);
}