package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNodeDevice;

public interface SeNodeDeviceDao {
    int deleteByPrimaryKey(String id);

    int insert(SeNodeDevice record);

    SeNodeDevice selectByPrimaryKey(String id);

    int updateByPrimaryKey(SeNodeDevice record);
}
