package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcConferenceChatLog;

public interface RvcConferenceChatLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(RvcConferenceChatLog record);

    int insertSelective(RvcConferenceChatLog record);

    RvcConferenceChatLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcConferenceChatLog record);

    int updateByPrimaryKey(RvcConferenceChatLog record);
}