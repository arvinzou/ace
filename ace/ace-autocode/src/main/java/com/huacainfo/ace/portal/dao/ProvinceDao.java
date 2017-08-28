package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Province;

public interface ProvinceDao {
    int deleteByPrimaryKey(String code);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
}