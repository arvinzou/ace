package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNodeMeter;

public interface SeNodeMeterDao {
    int deleteByPrimaryKey(String id);

    int insert(SeNodeMeter record);

    SeNodeMeter selectByPrimaryKey(String id);

    int updateByPrimaryKey(SeNodeMeter record);

    int allClear();
}
