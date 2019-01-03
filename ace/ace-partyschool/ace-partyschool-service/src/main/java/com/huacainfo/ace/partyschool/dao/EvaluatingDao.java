package com.huacainfo.ace.partyschool.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.Evaluating;
import com.huacainfo.ace.partyschool.vo.EvaluatingQVo;
import com.huacainfo.ace.partyschool.vo.EvaluatingVo;

public interface EvaluatingDao {

    Evaluating selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Evaluating record);


    int updateByPrimaryKey(Evaluating record);


    EvaluatingVo selectVoByPrimaryKey(String id);

    List
            <EvaluatingVo> findList(@Param("condition") EvaluatingQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") EvaluatingQVo condition);

    int isExit(Evaluating record);


}