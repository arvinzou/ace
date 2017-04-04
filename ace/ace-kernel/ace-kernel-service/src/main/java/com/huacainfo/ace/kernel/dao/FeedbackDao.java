package com.huacainfo.ace.kernel.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.kernel.model.Feedback;
import com.huacainfo.ace.kernel.vo.FeedbackQVo;
import com.huacainfo.ace.kernel.vo.FeedbackVo;

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