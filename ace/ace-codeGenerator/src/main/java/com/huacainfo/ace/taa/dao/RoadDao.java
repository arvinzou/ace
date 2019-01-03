package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.Road;

public interface RoadDao {
    int deleteByPrimaryKey(String id);

    int insert(Road record);

    int insertSelective(Road record);

    Road selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Road record);

    int updateByPrimaryKey(Road record);
}