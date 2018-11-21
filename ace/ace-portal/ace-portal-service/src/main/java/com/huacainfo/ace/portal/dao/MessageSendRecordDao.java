package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.MessageSendRecord;

public interface MessageSendRecordDao {
    int deleteByPrimaryKey(String id);

    int insert(MessageSendRecord record);

    int insertSelective(MessageSendRecord record);

    MessageSendRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MessageSendRecord record);

    int updateByPrimaryKey(MessageSendRecord record);
}