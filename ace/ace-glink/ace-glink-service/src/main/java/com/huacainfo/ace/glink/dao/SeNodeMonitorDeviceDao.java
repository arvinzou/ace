package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNodeMonitorDevice;

import java.util.List;

public interface SeNodeMonitorDeviceDao {
    int deleteByPrimaryKey(String id);

    int insert(SeNodeMonitorDevice record);

    SeNodeMonitorDevice selectByPrimaryKey(String id);

    int updateByPrimaryKey(SeNodeMonitorDevice record);

    List<SeNodeMonitorDevice> findByNodeID(String nodeID);
}
