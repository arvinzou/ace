package com.huacainfo.ace.partyschool.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.Course;
import com.huacainfo.ace.partyschool.vo.CourseQVo;
import com.huacainfo.ace.partyschool.vo.CourseVo;

public interface CourseDao {

    Course selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Course record);


    int updateByPrimaryKey(Course record);


    CourseVo selectVoByPrimaryKey(String id);

    List<CourseVo> findList(@Param("condition") CourseQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CourseQVo condition);

    int isExit(Course record);

    int updateStatus(Course record);

}