package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.PersonInfo;
import com.huacainfo.ace.society.vo.PersonInfoQVo;
import com.huacainfo.ace.society.vo.PersonInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonInfoDao {

    PersonInfo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(PersonInfo record);

    int insertSelective(PersonInfo record);

    int updateByPrimaryKey(PersonInfo record);

    int updateByPrimaryKeySelective(PersonInfo record);

    PersonInfoVo selectVoByPrimaryKey(String id);

    List<PersonInfoVo> findList(@Param("condition") PersonInfoQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") PersonInfoQVo condition);

    int isExit(PersonInfo record);

}