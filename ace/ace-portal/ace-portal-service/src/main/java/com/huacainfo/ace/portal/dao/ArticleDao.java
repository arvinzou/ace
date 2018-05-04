package com.huacainfo.ace.portal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Article;
import com.huacainfo.ace.portal.vo.ArticleQVo;
import com.huacainfo.ace.portal.vo.ArticleVo;

public interface ArticleDao {
    int deleteByPrimaryKey(String ArticleId);

    int insert(Article record);


    ArticleVo selectByPrimaryKey(String ArticleId);


    int updateByPrimaryKey(Article record);
    
    List<ArticleVo> findList(@Param("condition") ArticleQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ArticleQVo condition);

	int isExit(Article record);

}