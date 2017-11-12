package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.DiaoYan;

public interface DiaoYanDao {
    int deleteByPrimaryKey(String diaoyanId);

    int insert(DiaoYan record);

    int insertSelective(DiaoYan record);

    DiaoYan selectByPrimaryKey(String diaoyanId);

    int updateByPrimaryKeySelective(DiaoYan record);

    int updateByPrimaryKey(DiaoYan record);
}