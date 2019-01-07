package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.Classes;
import com.huacainfo.ace.partyschool.vo.ClassesQVo;
import com.huacainfo.ace.partyschool.vo.ClassesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassesDao {

    Classes selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Classes record);


    int updateByPrimaryKey(Classes record);


    ClassesVo selectVoByPrimaryKey(String id);

    List<ClassesVo> findList(@Param("condition") ClassesQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ClassesQVo condition);

    int isExit(Classes record);

    int updateStatus(Classes record);

}