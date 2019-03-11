package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.Course;
import com.huacainfo.ace.policeschool.vo.CourseQVo;
import com.huacainfo.ace.policeschool.vo.CourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    int isExit(@Param("condition") Course record);

    int updateStatus(Course record);

}