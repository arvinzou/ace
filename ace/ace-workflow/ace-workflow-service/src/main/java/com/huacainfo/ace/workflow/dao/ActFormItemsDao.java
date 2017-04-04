package com.huacainfo.ace.workflow.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.workflow.model.ActFormItems;

public interface ActFormItemsDao {
    int deleteByPrimaryKey(String ActFormItemsId);

    int insert(ActFormItems record);


    ActFormItems selectByPrimaryKey(String ActFormItemsId);


    int updateByPrimaryKey(ActFormItems record);


	int isExit(ActFormItems record);
	
	
	List<Map<String,Object>> selectListByFormId(String formId);
}