package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.ClassSchedule;
import com.huacainfo.ace.policeschool.vo.ClassScheduleQVo;
import com.huacainfo.ace.policeschool.vo.ClassScheduleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClassScheduleDao {

    ClassSchedule selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ClassSchedule record);


    int updateByPrimaryKey(ClassSchedule record);


    ClassScheduleVo selectVoByPrimaryKey(String id);

    List<ClassScheduleVo> findList(@Param("condition") ClassScheduleQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    List<ClassScheduleVo> LearnedCourses(@Param("condition") ClassScheduleQVo condition,
                                         @Param("start") int start,
                                         @Param("limit") int limit,
                                         @Param("orderBy") String orderBy);

    List<ClassScheduleVo> notDoneTestList(@Param("condition") ClassScheduleQVo condition,
                                          @Param("start") int start,
                                          @Param("limit") int limit,
                                          @Param("orderBy") String orderBy);

    List<ClassScheduleVo> doneTestList(@Param("condition") ClassScheduleQVo condition,
                                       @Param("start") int start,
                                       @Param("limit") int limit,
                                       @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ClassScheduleQVo condition);

    int notDoneTestSize(@Param("condition") ClassScheduleQVo condition);

    int isExit(ClassSchedule record);

    int updateStatus(@Param("id") String id,
                     @Param("status") String status);

    Map<String, Object> dataReport(Map<String, String> params);

    Map<String, Object> chartReport(@Param("teacherId") String teacherId,
                                    @Param("yearStr") String yearStr);
}
