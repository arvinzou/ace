package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.TraAccMtype;

public interface TraAccMtypeDao {
    int deleteByPrimaryKey(String id);

    int insert(TraAccMtype record);

    TraAccMtype selectByPrimaryKey(String id);

    int updateByPrimaryKey(TraAccMtype record);
}