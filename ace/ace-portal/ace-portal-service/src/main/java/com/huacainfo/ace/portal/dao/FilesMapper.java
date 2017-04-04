package com.huacainfo.ace.portal.dao;

import java.util.List;

import com.huacainfo.ace.portal.model.Files;

public interface FilesMapper {
    int deleteByPrimaryKey(String filePath);

    int insert(Files record);

    int insertSelective(Files record);

    Files selectByPrimaryKey(String filePath);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);
    
    List<Files> selectListByStatus(String status);
}