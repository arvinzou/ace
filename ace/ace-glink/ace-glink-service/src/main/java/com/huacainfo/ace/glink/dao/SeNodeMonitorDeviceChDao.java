package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNodeMonitorDeviceCh;
import org.apache.ibatis.annotations.Param;

public interface SeNodeMonitorDeviceChDao {
    int deleteByPrimaryKey(String id);

    int insert(SeNodeMonitorDeviceCh record);

    SeNodeMonitorDeviceCh selectByPrimaryKey(String id);

    int updateByPrimaryKey(SeNodeMonitorDeviceCh record);

    SeNodeMonitorDeviceCh findByCHName(@Param("deviceCode") String deviceCode,
                                       @Param("chName") String chName);
}
