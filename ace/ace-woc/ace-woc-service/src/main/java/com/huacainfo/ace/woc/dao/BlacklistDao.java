package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Blacklist;

public interface BlacklistDao {
    int deleteByPrimaryKey(String id);

    int insert(Blacklist record);

    int insertSelective(Blacklist record);

    Blacklist selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Blacklist record);

    int updateByPrimaryKey(Blacklist record);
}