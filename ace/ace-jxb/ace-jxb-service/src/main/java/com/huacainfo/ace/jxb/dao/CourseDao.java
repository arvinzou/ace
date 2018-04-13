package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.Course;
import com.huacainfo.ace.jxb.vo.CourseQVo;
import com.huacainfo.ace.jxb.vo.CourseVo;

public interface CourseDao {

    CourseVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Course record);



    int updateByPrimaryKey(Course record);



    
    List<CourseVo> findList(@Param("condition") CourseQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") CourseQVo condition);

	int isExit(Course record);

}