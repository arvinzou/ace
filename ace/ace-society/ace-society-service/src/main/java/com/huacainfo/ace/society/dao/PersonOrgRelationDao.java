package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.PersonOrgRelation;

public interface PersonOrgRelationDao {
    int deleteByPrimaryKey(String id);

    int insert(PersonOrgRelation record);

    PersonOrgRelation selectByPrimaryKey(String id);

    int updateByPrimaryKey(PersonOrgRelation record);
}