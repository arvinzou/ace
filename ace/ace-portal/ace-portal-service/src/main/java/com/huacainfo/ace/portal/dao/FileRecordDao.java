package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.FileRecord;

public interface FileRecordDao {

    int deleteByPrimaryKey(String id);

    int insert(FileRecord record);


    FileRecord selectByPrimaryKey(String id);


    int updateByPrimaryKey(FileRecord record);

    int isExit(FileRecord record);
}