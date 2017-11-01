package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.PerHp;

public interface PerHpDao {
    int deleteByPrimaryKey(String id);

    int insert(PerHp record);

    int insertSelective(PerHp record);

    PerHp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PerHp record);

    int updateByPrimaryKey(PerHp record);
}