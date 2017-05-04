package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.NormData;

public interface NormDataDao {
    int deleteByPrimaryKey(String id);

    int insert(NormData record);

    int insertSelective(NormData record);

    NormData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NormData record);

    int updateByPrimaryKey(NormData record);
}