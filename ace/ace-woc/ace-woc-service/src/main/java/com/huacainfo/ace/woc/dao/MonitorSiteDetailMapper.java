package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.MonitorSiteDetail;

public interface MonitorSiteDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(MonitorSiteDetail record);

    int insertSelective(MonitorSiteDetail record);

    MonitorSiteDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MonitorSiteDetail record);

    int updateByPrimaryKey(MonitorSiteDetail record);
}