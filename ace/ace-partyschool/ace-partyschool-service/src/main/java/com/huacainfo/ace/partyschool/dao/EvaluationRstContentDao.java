package com.huacainfo.ace.partyschool.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.EvaluationRstContent;
import com.huacainfo.ace.partyschool.vo.EvaluationRstContentQVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstContentVo;

public interface EvaluationRstContentDao {

EvaluationRstContent selectByPrimaryKey(String id);

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