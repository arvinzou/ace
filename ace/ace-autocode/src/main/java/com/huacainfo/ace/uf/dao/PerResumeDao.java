package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.PerResume;

public interface PerResumeDao {
    int deleteByPrimaryKey(String id);

    int insert(PerResume record);

    int insertSelective(PerResume record);

    PerResume selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PerResume record);

    int updateByPrimaryKey(PerResume record);
}