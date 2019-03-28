package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.CourseTeacher;
import org.apache.ibatis.annotations.Param;

public interface CourseTeacherDao {
    int insert(CourseTeacher record);

    int deleteByPrimaryKey(@Param("cid") String cid);

    int insertSelective(CourseTeacher record);
}