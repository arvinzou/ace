package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.NormDetail;

public interface NormDetailDao {
    int deleteByPrimaryKey(String id);

    int insert(NormDetail record);

    int insertSelective(NormDetail record);

    NormDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NormDetail record);

    int updateByPrimaryKey(NormDetail record);
}