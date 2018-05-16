package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopQuestionnaireResult;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultQVo;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FopQuestionnaireResultDao {

    FopQuestionnaireResult selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopQuestionnaireResult record);

    int insertSelective(FopQuestionnaireResult record);

    int updateByPrimaryKey(FopQuestionnaireResult record);

    int updateByPrimaryKeySelective(FopQuestionnaireResult record);

    FopQuestionnaireResultVo selectVoByPrimaryKey(String id);

    List<Map<String, Integer>> statisticalData(String opinionType);

    List<FopQuestionnaireResultVo> findList(@Param("condition") FopQuestionnaireResultQVo condition,
                                            @Param("start") int start,
                                            @Param("limit") int limit,
                                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopQuestionnaireResultQVo condition);

    int isExit(FopQuestionnaireResult record);

}