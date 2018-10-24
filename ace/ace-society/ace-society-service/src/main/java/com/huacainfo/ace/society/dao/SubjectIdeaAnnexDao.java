package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.SubjectIdeaAnnex;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexQVo;
import com.huacainfo.ace.society.vo.SubjectIdeaAnnexVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectIdeaAnnexDao {

    SubjectIdeaAnnex selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SubjectIdeaAnnex record);

    int insertSelective(SubjectIdeaAnnex record);

    int updateByPrimaryKey(SubjectIdeaAnnex record);

    int updateByPrimaryKeySelective(SubjectIdeaAnnex record);

    SubjectIdeaAnnexVo selectVoByPrimaryKey(String id);

    List<SubjectIdeaAnnexVo> findList(@Param("condition") SubjectIdeaAnnexQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SubjectIdeaAnnexQVo condition);

    int isExit(SubjectIdeaAnnex record);

}