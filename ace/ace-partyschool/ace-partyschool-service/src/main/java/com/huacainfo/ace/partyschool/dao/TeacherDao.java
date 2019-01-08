package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.Teacher;
import com.huacainfo.ace.partyschool.vo.TeacherQVo;
import com.huacainfo.ace.partyschool.vo.TeacherVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherDao {

    Teacher selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Teacher record);


    int updateByPrimaryKey(Teacher record);


    TeacherVo selectVoByPrimaryKey(String id);

    List<TeacherVo> findList(@Param("condition") TeacherQVo condition,
                             @Param("start") int start,
                             @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TeacherQVo condition);

    int isExist(Teacher record);

    int updateStatus(@Param("id") String id,
                     @Param("status") String status);

    int isExistByIdCard(String idCard);

    int isExistOtherIdCard(@Param("id") String id,
                           @Param("idCard") String idCard);
}