package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.Feedback;
import com.huacainfo.ace.uf.vo.FeedbackQVo;
import com.huacainfo.ace.uf.vo.FeedbackVo;

public interface FeedbackDao {
    int deleteByPrimaryKey(String FeedbackId);

    int insert(Feedback record);


    FeedbackVo selectByPrimaryKey(String FeedbackId);


    int updateByPrimaryKey(Feedback record);
    
    List<FeedbackVo> findList(@Param("condition") FeedbackQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") FeedbackQVo condition);

	int isExit(Feedback record);

}