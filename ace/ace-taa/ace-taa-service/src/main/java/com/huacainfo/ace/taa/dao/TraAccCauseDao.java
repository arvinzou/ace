package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.TraAccCause;

public interface TraAccCauseDao {
    int deleteByPrimaryKey(String id);

    int insert(TraAccCause record);

    TraAccCause selectByPrimaryKey(String id);

    int updateByPrimaryKey(TraAccCause record);

    int reset(String accId);
}