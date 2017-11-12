package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.PeiXun;

public interface PeiXunDao {
    int deleteByPrimaryKey(String peixunId);

    int insert(PeiXun record);

    int insertSelective(PeiXun record);

    PeiXun selectByPrimaryKey(String peixunId);

    int updateByPrimaryKeySelective(PeiXun record);

    int updateByPrimaryKey(PeiXun record);
}