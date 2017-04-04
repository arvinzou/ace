package com.huacainfo.ace.workflow.dao.field;

import java.util.Map;

import com.huacainfo.ace.workflow.model.field.ActFormTextbox;

public interface ActFormTextboxDao {
    int deleteByPrimaryKey(String id);

    int insert(ActFormTextbox record);

    Map<String,Object> selectByPrimaryKey(String id);

    int updateByPrimaryKey(ActFormTextbox record);
    
    int isExit(ActFormTextbox record);
}