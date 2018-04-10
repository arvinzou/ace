package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.ExamScoreSub;
import com.huacainfo.ace.jxb.vo.ExamScoreSubQVo;
import com.huacainfo.ace.jxb.vo.ExamScoreSubVo;

public interface ExamScoreSubDao {

    ExamScoreSubVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ExamScoreSub record);

    int insertSelective(ExamScoreSub record);

    int updateByPrimaryKey(ExamScoreSub record);

    int updateByPrimaryKeySelective(ExamScoreSub record);

    
    List<ExamScoreSubVo> findList(@Param("condition") ExamScoreSubQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ExamScoreSubQVo condition);

	int isExit(ExamScoreSub record);

}