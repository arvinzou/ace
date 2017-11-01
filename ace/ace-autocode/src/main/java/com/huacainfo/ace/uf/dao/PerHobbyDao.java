package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.PerHobby;

public interface PerHobbyDao {
    int deleteByPrimaryKey(String id);

    int insert(PerHobby record);

    int insertSelective(PerHobby record);

    PerHobby selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PerHobby record);

    int updateByPrimaryKey(PerHobby record);
}