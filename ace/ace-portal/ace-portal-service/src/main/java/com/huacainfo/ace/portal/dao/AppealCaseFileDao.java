package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.AppealCaseFile;

public interface AppealCaseFileDao {
    int deleteByPrimaryKey(String id);

    int insert(AppealCaseFile record);

}