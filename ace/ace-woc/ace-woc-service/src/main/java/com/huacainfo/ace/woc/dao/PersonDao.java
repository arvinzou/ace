package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Person;
import com.huacainfo.ace.woc.vo.PersonQVo;
import com.huacainfo.ace.woc.vo.PersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PersonDao {
    int deleteByPrimaryKey(String id);

    int insert(Person record);

    int insertSelective(Person record);

    PersonVo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    List<PersonVo> findList(@Param("condition") PersonQVo condition,
                            @Param("start") int start, @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") PersonQVo condition);

    int isExit(Person record);

    List<Map<String, String>> selectPerson(
            @Param("params") Map<String, Object> params);
}