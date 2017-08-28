package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.ActivityUser;

public interface ActivityUserDao {
    int deleteByPrimaryKey(String id);

    int insert(ActivityUser record);

    int insertSelective(ActivityUser record);

    ActivityUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityUser record);

    int updateByPrimaryKey(ActivityUser record);
}