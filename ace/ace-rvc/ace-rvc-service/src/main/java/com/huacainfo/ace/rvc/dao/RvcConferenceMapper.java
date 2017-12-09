package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcConference;

public interface RvcConferenceMapper {
    int deleteByPrimaryKey(String id);

    int insert(RvcConference record);

    int insertSelective(RvcConference record);

    RvcConference selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcConference record);

    int updateByPrimaryKey(RvcConference record);
}