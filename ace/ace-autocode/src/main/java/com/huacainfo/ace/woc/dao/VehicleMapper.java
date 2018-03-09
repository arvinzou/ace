package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Vehicle;

public interface VehicleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
}