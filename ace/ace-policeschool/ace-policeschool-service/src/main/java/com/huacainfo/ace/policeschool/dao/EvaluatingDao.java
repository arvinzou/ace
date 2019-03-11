package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.Evaluating;
import com.huacainfo.ace.policeschool.vo.EvaluatingQVo;
import com.huacainfo.ace.policeschool.vo.EvaluatingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluatingDao {

    Evaluating selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Evaluating record);


    int updateByPrimaryKey(Evaluating record);


    EvaluatingVo selectVoByPrimaryKey(String id);

    List<EvaluatingVo> findList(@Param("condition") EvaluatingQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") EvaluatingQVo condition);

    int isExit(@Param("condition") Evaluating record);


}