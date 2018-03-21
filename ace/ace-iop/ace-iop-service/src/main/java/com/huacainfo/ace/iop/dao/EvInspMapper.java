package com.huacainfo.ace.iop.dao;

import com.huacainfo.ace.iop.model.EvInsp;

public interface EvInspMapper {
    int deleteByPrimaryKey(String evInspId);
    int deleteByTaskId(String id);

    int insert(EvInsp record);

    int insertSelective(EvInsp record);

    EvInsp selectByPrimaryKey(String evInspId);

    int updateByPrimaryKeySelective(EvInsp record);

    int updateByPrimaryKey(EvInsp record);
}