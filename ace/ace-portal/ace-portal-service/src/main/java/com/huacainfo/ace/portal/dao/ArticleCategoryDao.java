package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.ArticleCategory;
import com.huacainfo.ace.portal.vo.ArticleCategoryQVo;
import com.huacainfo.ace.portal.vo.ArticleCategoryVo;

public interface ArticleCategoryDao {
    int deleteByPrimaryKey(String ArticleCategoryId);

    int insert(ArticleCategory record);


    ArticleCategoryVo selectByPrimaryKey(String ArticleCategoryId);


    int updateByPrimaryKey(ArticleCategory record);
    
    List<ArticleCategoryVo> findList(@Param("condition") ArticleCategoryQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ArticleCategoryQVo condition);

	int isExit(ArticleCategory record);

    List<Map<String,Object>> getList(Map<String,Object> params);

    List<Map<String,Object>> getListById(String id);

}