package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.ComDataImport;

import java.util.List;

public interface ComDataImportDao {
    int deleteByPrimaryKey(String creditCode);

    int insert(ComDataImport record);

    int insertSelective(ComDataImport record);

    ComDataImport selectByPrimaryKey(String creditCode);

    int updateByPrimaryKeySelective(ComDataImport record);

    int updateByPrimaryKey(ComDataImport record);

    List<ComDataImport> findList();

}