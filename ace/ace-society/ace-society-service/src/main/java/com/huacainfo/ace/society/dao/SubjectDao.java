package com.huacainfo.ace.society.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.society.model.Subject;
import com.huacainfo.ace.society.vo.SubjectQVo;
import com.huacainfo.ace.society.vo.SubjectVo;

public interface SubjectDao {

Subject selectByPrimaryKey(String id);

int deleteByPrimaryKey(String id);

int insert(Subject record);

int insertSelective(Subject record);

int updateByPrimaryKey(Subject record);

int updateByPrimaryKeySelective(Subject record);

SubjectVo selectVoByPrimaryKey(String id);

List
<SubjectVo> findList(@Param("condition") SubjectQVo condition,
    @Param("start") int start,
    @Param("limit") int limit,
    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SubjectQVo condition);

    int isExit(Subject record);

    }