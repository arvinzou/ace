package com.huacainfo.ace.live.dao;

import com.huacainfo.ace.live.model.LiveMsg;

public interface LiveMsgDao {
    int deleteByPrimaryKey(String id);

    int insert(LiveMsg record);

    int insertSelective(LiveMsg record);

    LiveMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LiveMsg record);

    int updateByPrimaryKeyWithBLOBs(LiveMsg record);

    int updateByPrimaryKey(LiveMsg record);
}