package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Road;

public interface RoadMapper {
    int deleteByPrimaryKey(String id);

    int insert(Road record);

    int insertSelective(Road record);

    Road selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Road record);

    int updateByPrimaryKey(Road record);
}