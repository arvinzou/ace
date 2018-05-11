package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopAiQuestion;
import com.huacainfo.ace.fop.vo.FopAiQuestionQVo;
import com.huacainfo.ace.fop.vo.FopAiQuestionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopAiQuestionDao {

    FopAiQuestion selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopAiQuestion record);

    int insertSelective(FopAiQuestion record);

    int updateByPrimaryKey(FopAiQuestion record);

    int updateByPrimaryKeySelective(FopAiQuestion record);

    FopAiQuestionVo selectVoByPrimaryKey(String id);

    List<FopAiQuestionVo> findList(@Param("condition") FopAiQuestionQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopAiQuestionQVo condition);

    int isExit(FopAiQuestion record);

}