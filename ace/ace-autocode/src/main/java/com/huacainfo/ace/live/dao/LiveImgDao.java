package com.huacainfo.ace.live.dao;

import com.huacainfo.ace.live.model.LiveImg;

public interface LiveImgDao {
    int deleteByPrimaryKey(String id);

    int insert(LiveImg record);

    int insertSelective(LiveImg record);

    LiveImg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LiveImg record);

    int updateByPrimaryKey(LiveImg record);
}