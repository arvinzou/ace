package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.NormCfg;

public interface NormCfgDao {
    int deleteByPrimaryKey(String id);

    int insert(NormCfg record);

    int insertSelective(NormCfg record);

    NormCfg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NormCfg record);

    int updateByPrimaryKey(NormCfg record);
}