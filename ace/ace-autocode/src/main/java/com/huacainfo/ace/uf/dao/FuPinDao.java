package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.FuPin;

public interface FuPinDao {
    int deleteByPrimaryKey(String id);

    int insert(FuPin record);

    int insertSelective(FuPin record);

    FuPin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FuPin record);

    int updateByPrimaryKey(FuPin record);
}