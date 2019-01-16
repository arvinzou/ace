package com.huacainfo.ace.partyschool.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.ClassSchedule;
import com.huacainfo.ace.partyschool.vo.ClassScheduleQVo;
import com.huacainfo.ace.partyschool.vo.ClassScheduleVo;

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

    int updateStatus(ClassSchedule record);

}