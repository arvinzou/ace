package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcConferenceMembers;

public interface RvcConferenceMembersMapper {
    int deleteByPrimaryKey(String id);

    int insert(RvcConferenceMembers record);

    int insertSelective(RvcConferenceMembers record);

    RvcConferenceMembers selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcConferenceMembers record);

    int updateByPrimaryKey(RvcConferenceMembers record);

    RvcConferenceMembers getByUserId(String userId);

}