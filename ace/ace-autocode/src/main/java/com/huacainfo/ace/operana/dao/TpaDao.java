package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.Tpa;

public interface TpaDao {
    int deleteByPrimaryKey(String id);

    int insert(Tpa record);

    int insertSelective(Tpa record);

    Tpa selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Tpa record);

    int updateByPrimaryKey(Tpa record);
}