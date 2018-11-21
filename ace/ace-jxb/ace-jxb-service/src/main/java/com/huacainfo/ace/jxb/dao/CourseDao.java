package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.Course;
import com.huacainfo.ace.jxb.vo.CourseQVo;
import com.huacainfo.ace.jxb.vo.CourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {

    Course selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Course record);

    int insertSelective(Course record);

    int updateByPrimaryKey(Course record);

    int updateByPrimaryKeySelective(Course record);

    CourseVo selectVoByPrimaryKey(String id);

    List<CourseVo> findList(@Param("condition") CourseQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CourseQVo condition);

    int isExit(Course record);

    int updateFine(@Param("id") String id,@Param("fine") String fine);

}