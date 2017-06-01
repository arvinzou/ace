package com.huacainfo.ace.luohua.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.luohua.model.Feedback;
import com.huacainfo.ace.luohua.vo.FeedbackQVo;
import com.huacainfo.ace.luohua.vo.FeedbackVo;

public interface FeedbackDao {
    int deleteByPrimaryKey(String FeedbackId);

    int insert(Feedback record);


    Feedback selectByPrimaryKey(String FeedbackId);


    int updateByPrimaryKey(Feedback record);
    
    List<FeedbackVo> findList(@Param("condition") FeedbackQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") FeedbackQVo condition);

	int isExit(Feedback record);

}