package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.EvaluationIndex;
import com.huacainfo.ace.policeschool.vo.EvaluationIndexQVo;
import com.huacainfo.ace.policeschool.vo.EvaluationIndexVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationIndexDao {

    EvaluationIndex selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int deleteByEvaluatingId(String evaluatingId);

    int insert(EvaluationIndex record);

    int updateByPrimaryKey(EvaluationIndex record);


    EvaluationIndexVo selectVoByPrimaryKey(String id);

    List<EvaluationIndexVo> findList(@Param("condition") EvaluationIndexQVo condition,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    List<EvaluationIndex> selectByEvaluatingId(String id);


    int findCount(@Param("condition") EvaluationIndexQVo condition);

    int isExit(EvaluationIndex record);

    int updateStatus(EvaluationIndex record);

}