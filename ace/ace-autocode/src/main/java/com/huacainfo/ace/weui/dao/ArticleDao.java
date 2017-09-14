package com.huacainfo.ace.weui.dao;

import com.huacainfo.ace.weui.model.Article;

public interface ArticleDao {
    int deleteByPrimaryKey(String id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
}