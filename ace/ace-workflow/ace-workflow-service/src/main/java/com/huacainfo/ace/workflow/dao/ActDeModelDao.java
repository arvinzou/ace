package com.huacainfo.ace.workflow.dao;

import com.huacainfo.ace.workflow.model.ActDeModel;

public interface ActDeModelDao {
    int deleteByPrimaryKey(String id);

    int insert(ActDeModel record);


    int updateByPrimaryKey(ActDeModel record);
    
    int isExit(ActDeModel record);
}