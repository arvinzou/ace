package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.Question;
import com.huacainfo.ace.jxb.vo.QuestionQVo;
import com.huacainfo.ace.jxb.vo.QuestionVo;

public interface QuestionDao {

    QuestionVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Question record);

    int insertSelective(Question record);

    int updateByPrimaryKey(Question record);

    int updateByPrimaryKeySelective(Question record);

    
    List<QuestionVo> findList(@Param("condition") QuestionQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") QuestionQVo condition);

	int isExit(Question record);

}