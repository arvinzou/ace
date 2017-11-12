package com.huacainfo.ace.operana.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.operana.model.MeetingFiles;

public interface MeetingFilesDao {
    int deleteByPrimaryKey(String id);

    int insert(MeetingFiles record);

    List<Map<String, Object>> selectFilesByMeetingId(String meetingId);
}