package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.MonitorSite;

public interface MonitorSiteMapper {
    int deleteByPrimaryKey(String id);

    int insert(MonitorSite record);

    int insertSelective(MonitorSite record);

    MonitorSite selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MonitorSite record);

    int updateByPrimaryKey(MonitorSite record);
}