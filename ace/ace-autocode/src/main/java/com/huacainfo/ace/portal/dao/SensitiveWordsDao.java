package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.SensitiveWords;

public interface SensitiveWordsDao {
    int deleteByPrimaryKey(String id);

    int insert(SensitiveWords record);

    int insertSelective(SensitiveWords record);

    SensitiveWords selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SensitiveWords record);

    int updateByPrimaryKey(SensitiveWords record);
}