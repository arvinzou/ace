package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.CoursePart;
import com.huacainfo.ace.jxb.vo.CoursePartQVo;
import com.huacainfo.ace.jxb.vo.CoursePartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoursePartDao {

    CoursePart selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CoursePart record);

    int insertSelective(CoursePart record);

    int updateByPrimaryKey(CoursePart record);

    int updateByPrimaryKeySelective(CoursePart record);

    CoursePartVo selectVoByPrimaryKey(String id);

    List<CoursePartVo> findList(@Param("condition") CoursePartQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CoursePartQVo condition);

    int isExit(CoursePart record);

    List<CoursePartVo> findByCourseId(String courseId);
}