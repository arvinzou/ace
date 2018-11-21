package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.CourseSource;
import com.huacainfo.ace.jxb.vo.CourseSourceQVo;
import com.huacainfo.ace.jxb.vo.CourseSourceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseSourceDao {

    CourseSource selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CourseSource record);

    int insertSelective(CourseSource record);

    int updateByPrimaryKey(CourseSource record);

    int updateByPrimaryKeySelective(CourseSource record);

    CourseSourceVo selectVoByPrimaryKey(String id);

    List<CourseSourceVo> findList(@Param("condition") CourseSourceQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CourseSourceQVo condition);

    int isExit(CourseSource record);

    List<CourseSource> findByCourseId(String courseId);
}