package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.MeetingFiles;

public interface MeetingFilesDao {
    int deleteByPrimaryKey(String id);

    int insert(MeetingFiles record);

    int insertSelective(MeetingFiles record);

    MeetingFiles selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeetingFiles record);

    int updateByPrimaryKey(MeetingFiles record);
}