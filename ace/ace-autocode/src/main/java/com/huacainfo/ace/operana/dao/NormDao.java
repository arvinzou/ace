package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.Norm;

public interface NormDao {
    int deleteByPrimaryKey(String id);

    int insert(Norm record);

    int insertSelective(Norm record);

    Norm selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Norm record);

    int updateByPrimaryKeyWithBLOBs(Norm record);

    int updateByPrimaryKey(Norm record);
}