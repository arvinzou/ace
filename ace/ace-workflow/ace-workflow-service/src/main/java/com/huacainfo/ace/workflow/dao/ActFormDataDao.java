package com.huacainfo.ace.workflow.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.workflow.model.ActFormData;

public interface ActFormDataDao {
    int deleteByPrimaryKey(String id);

    int insert(ActFormData record);

    ActFormData selectByPrimaryKey(String id);

    int updateByPrimaryKey(ActFormData record);
    
    int isExit(ActFormData record);
    
    List<Map<String,Object>> selectListByInstId(String instId);
    
    List<Map<String,Object>> selectModelByAppId(String appId);
    
}