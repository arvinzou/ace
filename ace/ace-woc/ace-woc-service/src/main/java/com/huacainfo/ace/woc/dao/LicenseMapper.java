package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.License;

public interface LicenseMapper {
    int deleteByPrimaryKey(String id);

    int insert(License record);

    int insertSelective(License record);

    License selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(License record);

    int updateByPrimaryKey(License record);
}