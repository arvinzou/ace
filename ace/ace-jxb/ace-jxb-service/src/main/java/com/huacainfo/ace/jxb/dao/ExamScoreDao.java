package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.ExamScore;
import com.huacainfo.ace.jxb.vo.ExamScoreQVo;
import com.huacainfo.ace.jxb.vo.ExamScoreVo;

public interface ExamScoreDao {

    ExamScoreVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ExamScore record);

    int insertSelective(ExamScore record);

    int updateByPrimaryKey(ExamScore record);

    int updateByPrimaryKeySelective(ExamScore record);

    
    List<ExamScoreVo> findList(@Param("condition") ExamScoreQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ExamScoreQVo condition);

	int isExit(ExamScore record);

}