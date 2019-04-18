package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNodeMonitor;

public interface SeNodeMonitorDao {
    int deleteByPrimaryKey(String id);

    int insert(SeNodeMonitor record);

    SeNodeMonitor selectByPrimaryKey(String id);

    int updateByPrimaryKey(SeNodeMonitor record);
}
