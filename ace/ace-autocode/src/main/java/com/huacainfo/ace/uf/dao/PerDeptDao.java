package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.PerDept;

public interface PerDeptDao {
    int deleteByPrimaryKey(String id);

    int insert(PerDept record);

    int insertSelective(PerDept record);

    PerDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PerDept record);

    int updateByPrimaryKey(PerDept record);
}