package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.ActivityComment;

public interface ActivityCommentDao {
    int deleteByPrimaryKey(String id);

    int insert(ActivityComment record);

    int insertSelective(ActivityComment record);

    ActivityComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityComment record);

    int updateByPrimaryKey(ActivityComment record);
}