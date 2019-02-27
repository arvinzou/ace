package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.Classroom;
import com.huacainfo.ace.policeschool.vo.ClassroomQVo;
import com.huacainfo.ace.policeschool.vo.ClassroomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassroomDao {

    Classroom selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Classroom record);


    int updateByPrimaryKey(Classroom record);


    ClassroomVo selectVoByPrimaryKey(String id);

    List<ClassroomVo> findList(@Param("condition") ClassroomQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ClassroomQVo condition);

    int isExit(Classroom record);

    int updateStatus(Classroom record);

}