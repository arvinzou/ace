package com.huacainfo.ace.partyschool.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.Classroom;
import com.huacainfo.ace.partyschool.vo.ClassroomQVo;
import com.huacainfo.ace.partyschool.vo.ClassroomVo;

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