package com.huacainfo.ace.partyschool.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.EvaluationRst;
import com.huacainfo.ace.partyschool.vo.EvaluationRstQVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstVo;

public interface EvaluationRstDao {

    EvaluationRst selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(EvaluationRst record);


    int updateByPrimaryKey(EvaluationRst record);


    EvaluationRstVo selectVoByPrimaryKey(String id);

    List<Map<String,String>> statistics(String classScheduleId);

    List<EvaluationRstVo> findList(@Param("condition") EvaluationRstQVo condition,
                                       @Param("start") int start,
                                       @Param("limit") int limit,
                                       @Param("orderBy") String orderBy);


    List<EvaluationRstVo> findListVo(@Param("condition") EvaluationRstQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") EvaluationRstQVo condition);
    int findCountVo(@Param("condition") EvaluationRstQVo condition);
    int getDoneSize(@Param("condition") EvaluationRstQVo condition);

    int isExit(EvaluationRst record);

    int updateStatus(EvaluationRst record);

}