package com.huacainfo.ace.workflow.dao.field;

import java.util.Map;

import com.huacainfo.ace.workflow.model.field.ActFormCombobox;

public interface ActFormComboboxDao {
    int deleteByPrimaryKey(String id);

    int insert(ActFormCombobox record);
    int updateByPrimaryKey(ActFormCombobox record);

    Map<String,Object> selectByPrimaryKey(String id);

    
    int isExit(ActFormCombobox record);
}