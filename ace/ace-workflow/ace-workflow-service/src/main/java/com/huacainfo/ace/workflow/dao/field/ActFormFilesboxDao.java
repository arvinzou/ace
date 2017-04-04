package com.huacainfo.ace.workflow.dao.field;

import java.util.Map;

import com.huacainfo.ace.workflow.model.field.ActFormFilesbox;

public interface ActFormFilesboxDao {
    int deleteByPrimaryKey(String id);

    int insert(ActFormFilesbox record);

    int updateByPrimaryKey(ActFormFilesbox record);
    
    Map<String,Object> selectByPrimaryKey(String id);
    int isExit(ActFormFilesbox record);
}