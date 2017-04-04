package com.huacainfo.ace.workflow.dao.field;

import java.util.Map;

import com.huacainfo.ace.workflow.model.field.ActFormGrouptitle;

public interface ActFormGrouptitleDao {
    int deleteByPrimaryKey(String id);

    int insert(ActFormGrouptitle record);


    Map<String,Object> selectByPrimaryKey(String id);


    int updateByPrimaryKey(ActFormGrouptitle record);
    
    
    int isExit(ActFormGrouptitle record);
}