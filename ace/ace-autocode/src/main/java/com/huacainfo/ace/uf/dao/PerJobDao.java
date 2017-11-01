package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.PerJob;

public interface PerJobDao {
    int deleteByPrimaryKey(String id);

    int insert(PerJob record);

    int insertSelective(PerJob record);

    PerJob selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PerJob record);

    int updateByPrimaryKey(PerJob record);
}