package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.SensitiveWords;
import com.huacainfo.ace.portal.vo.SensitiveWordsVo;

import java.util.List;

public interface SensitiveWordsDao {

    int deleteByPrimaryKey(String id);

    int insert(SensitiveWords record);

    int insertSelective(SensitiveWords record);

    SensitiveWords selectByPrimaryKey(String id);

    List<SensitiveWordsVo> findSensitiveWordsList(SensitiveWords record);

    int updateByPrimaryKeySelective(SensitiveWords record);

    int updateByPrimaryKey(SensitiveWords record);
}