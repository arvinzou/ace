package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcConferenceRes;

public interface RvcConferenceResDao {
    int deleteByPrimaryKey(String id);

    int insert(RvcConferenceRes record);

    int insertSelective(RvcConferenceRes record);

    RvcConferenceRes selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcConferenceRes record);

    int updateByPrimaryKey(RvcConferenceRes record);
}