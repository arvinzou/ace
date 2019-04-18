package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNodeMonitorDeviceCh;

public interface SeNodeMonitorDeviceChDao {
    int deleteByPrimaryKey(String id);

    int insert(SeNodeMonitorDeviceCh record);

    int insertSelective(SeNodeMonitorDeviceCh record);

    SeNodeMonitorDeviceCh selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SeNodeMonitorDeviceCh record);

    int updateByPrimaryKey(SeNodeMonitorDeviceCh record);
}