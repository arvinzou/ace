package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.Classes;
import com.huacainfo.ace.partyschool.vo.ClassesQVo;
import com.huacainfo.ace.partyschool.vo.ClassesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClassesDao {

    Classes selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Classes record);


    int updateByPrimaryKey(Classes record);


    ClassesVo selectVoByPrimaryKey(String id);

    ClassesVo getClassesInfo(String id);

    List<ClassesVo> findList(@Param("condition") ClassesQVo condition,
                             @Param("start") int start,
                             @Param("limit") int limit,
                             @Param("orderBy") String orderBy);


    List<ClassesVo> findMyClassesList(String teacherId);

    List<ClassesVo> findAllClassesList();

    int findCount(@Param("condition") ClassesQVo condition);

    int isExit(Classes record);

    int updateStatus(Classes record);

    List<Map<String, String>> findByQ(@Param("params") Map<String, Object> params);

    int headmasterCount(@Param("id") String id, @Param("htId") String htId);
}