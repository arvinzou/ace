package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.FileRecord;

import org.apache.ibatis.annotations.Param;
import java.util.*;
public interface FileRecordDao {

    int deleteByPrimaryKey(String id);

    int insert(FileRecord record);


    FileRecord selectByPrimaryKey(String id);


    int updateByPrimaryKey(FileRecord record);

    int isExit(FileRecord record);

    int updateUrl(@Param("id")String id,@Param("url")String url);


    List<Map<String,Object>> selectWaitList();
}