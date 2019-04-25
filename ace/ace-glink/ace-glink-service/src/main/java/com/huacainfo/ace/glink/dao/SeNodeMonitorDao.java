package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNodeMonitor;
import com.huacainfo.ace.glink.vo.SeNodeMonitorVo;

public interface SeNodeMonitorDao {
    int deleteByPrimaryKey(String id);

    int insert(SeNodeMonitor record);

    SeNodeMonitor selectByPrimaryKey(String id);

    int updateByPrimaryKey(SeNodeMonitor record);

    int allClear();

    SeNodeMonitorVo findByNodeID(String nodeID);

}
