package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Site;

public interface SiteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);
}