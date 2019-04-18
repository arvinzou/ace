package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNodeMonitorDevice;

public interface SeNodeMonitorDeviceDao {
    int deleteByPrimaryKey(String id);

    int insert(SeNodeMonitorDevice record);

    SeNodeMonitorDevice selectByPrimaryKey(String id);

    int updateByPrimaryKey(SeNodeMonitorDevice record);
}
