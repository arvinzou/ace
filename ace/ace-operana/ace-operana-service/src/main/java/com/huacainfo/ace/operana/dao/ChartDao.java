package com.huacainfo.ace.operana.dao;

/**
 * Created by chenxiaoke on 2017/5/8.
 */
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.List;
public interface ChartDao {

    Map<String,java.math.BigDecimal> selectNormDataByMeetingIdTopicIdNormId(@Param("meetingId") String meetingId, @Param("topicId") String topicId, @Param("normId") String normId);

    java.math.BigDecimal selectNormIndex( @Param("year") int year, @Param("normId") String normId);

    List<Map<String,Object>> selectTop10Problem(@Param("meetingId") String meetingId, @Param("topicId") String topicId, @Param("normId") String normId, @Param("cwk") String cwk);
    List<Map<String,Object>> selectTop10ProblemDetail(@Param("meetingId") String meetingId, @Param("topicId") String topicId, @Param("normId") String normId, @Param("cwk") String cwk, @Param("list")List<String> list);

    List<Map<String,Object>> selectTpa(@Param("meetingId") String meetingId, @Param("topicId") String topicId, @Param("normId") String normId, @Param("cwk") String cwk);
}
