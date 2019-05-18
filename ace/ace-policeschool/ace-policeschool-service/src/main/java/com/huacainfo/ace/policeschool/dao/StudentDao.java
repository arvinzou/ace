package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.Student;
import com.huacainfo.ace.policeschool.vo.StudentQVo;
import com.huacainfo.ace.policeschool.vo.StudentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    Student selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int updateByPrimaryKey(Student record);


    StudentVo selectVoByPrimaryKey(String id);

    List<StudentVo> findList(@Param("condition") StudentQVo condition,
                             @Param("start") int start,
                             @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") StudentQVo condition);

    int findStudentCount(String classId);

    int isExist(Student record);

    int updateStatus(@Param("id") String id,
                     @Param("status") String status);

    int isExistByIdCard(String idCard);

    int isExistOtherIdCard(@Param("id") String id,
                           @Param("idCard") String idCard);

    int isExistOtherMobile(@Param("id") String id,
                           @Param("mobile") String mobile);

    Map<String, String> selectUserClassInfo(String id);

    String selectTeacherClassInfoById(String id);


    List<Map<String, String>> selectTeacherClasses(String id);

    Student findByBadgeNum(String badgeNum);
}
