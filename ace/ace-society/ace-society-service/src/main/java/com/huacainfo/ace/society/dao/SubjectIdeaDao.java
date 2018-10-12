package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.SubjectIdea;
import com.huacainfo.ace.society.vo.SubjectIdeaQVo;
import com.huacainfo.ace.society.vo.SubjectIdeaVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectIdeaDao {

    SubjectIdea selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SubjectIdea record);

    int insertSelective(SubjectIdea record);

    int updateByPrimaryKey(SubjectIdea record);

    int updateByPrimaryKeySelective(SubjectIdea record);

    SubjectIdeaVo selectVoByPrimaryKey(String id);

    List<SubjectIdeaVo> findList(@Param("condition") SubjectIdeaQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SubjectIdeaQVo condition);

    int isExit(SubjectIdea record);

    int updateStatus(SubjectIdea record);
}