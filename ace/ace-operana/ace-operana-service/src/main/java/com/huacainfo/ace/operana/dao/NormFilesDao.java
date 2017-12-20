package com.huacainfo.ace.operana.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.operana.model.NormFiles;
import com.huacainfo.ace.operana.vo.NormFilesQVo;
import com.huacainfo.ace.operana.vo.NormFilesVo;

public interface NormFilesDao {
    int deleteByPrimaryKey(String id);

    int insert(NormFiles record);



    List<Map<String, Object>> selectFilesByMeetingTopicNormId(@Param("meetingId") String meetingId, @Param("topicId") String topicId, @Param("normId") String normId);

}