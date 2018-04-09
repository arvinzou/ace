package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.Teacher;
import com.huacainfo.ace.jxb.vo.TeacherQVo;
import com.huacainfo.ace.jxb.vo.TeacherVo;

public interface TeacherDao {

    TeacherVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    int updateByPrimaryKeySelective(Teacher record);

    
    List<TeacherVo> findList(@Param("condition") TeacherQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TeacherQVo condition);

	int isExit(Teacher record);

}