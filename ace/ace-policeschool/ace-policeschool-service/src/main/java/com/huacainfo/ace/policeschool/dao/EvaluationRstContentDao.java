package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.EvaluationRstContent;
import com.huacainfo.ace.policeschool.vo.EvaluationRstContentQVo;
import com.huacainfo.ace.policeschool.vo.EvaluationRstContentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationRstContentDao {

    EvaluationRstContent selectByPrimaryKey(String id);

    EvaluationRstContent selectEvaluationRstContent(@Param("condition") EvaluationRstContent condition);

    int deleteByPrimaryKey(String id);

    int insert(EvaluationRstContent record);


    int updateByPrimaryKey(EvaluationRstContent record);


    EvaluationRstContentVo selectVoByPrimaryKey(String id);

    List
            <EvaluationRstContentVo> findList(@Param("condition") EvaluationRstContentQVo condition,
                                              @Param("start") int start,
                                              @Param("limit") int limit,
                                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") EvaluationRstContentQVo condition);

    int isExit(EvaluationRstContent record);

    int updateStatus(EvaluationRstContent record);

}