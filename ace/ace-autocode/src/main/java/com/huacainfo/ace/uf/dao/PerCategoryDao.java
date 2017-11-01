package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.PerCategory;

public interface PerCategoryDao {
    int deleteByPrimaryKey(String id);

    int insert(PerCategory record);

    int insertSelective(PerCategory record);

    PerCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PerCategory record);

    int updateByPrimaryKey(PerCategory record);
}