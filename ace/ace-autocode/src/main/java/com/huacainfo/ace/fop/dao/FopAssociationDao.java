package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopAssociation;

public interface FopAssociationDao {
    int deleteByPrimaryKey(String id);

    int insert(FopAssociation record);

    int insertSelective(FopAssociation record);

    FopAssociation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FopAssociation record);

    int updateByPrimaryKey(FopAssociation record);
}