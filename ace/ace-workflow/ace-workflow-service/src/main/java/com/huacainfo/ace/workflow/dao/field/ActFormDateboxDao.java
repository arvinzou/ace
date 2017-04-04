package com.huacainfo.ace.workflow.dao.field;

import java.util.Map;

import com.huacainfo.ace.workflow.model.field.ActFormDatebox;

public interface ActFormDateboxDao {
    int deleteByPrimaryKey(String id);

    int insert(ActFormDatebox record);

    int updateByPrimaryKey(ActFormDatebox record);
    
    Map<String,Object> selectByPrimaryKey(String id);
    int isExit(ActFormDatebox record);
}