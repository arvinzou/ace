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

    /**
     * 功能描述: 根据关键字，查询问题答案
     * <p> 目前仅市工商联使用，查询是工商联数据库数据</p>
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/16 11:54
     */
    List<FopAiQuestion> findQuestion(String userInput);
}