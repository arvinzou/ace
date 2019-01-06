package com.huacainfo.ace.partyschool.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.EvaluationIndex;
import com.huacainfo.ace.partyschool.vo.EvaluationIndexQVo;
import com.huacainfo.ace.partyschool.vo.EvaluationIndexVo;

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