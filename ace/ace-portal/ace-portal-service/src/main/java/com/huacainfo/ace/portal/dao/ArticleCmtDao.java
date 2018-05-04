package com.huacainfo.ace.portal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.ArticleCmt;
import com.huacainfo.ace.portal.vo.ArticleCmtQVo;
import com.huacainfo.ace.portal.vo.ArticleCmtVo;

public interface ArticleCmtDao {
    int deleteByPrimaryKey(String ArticleCmtId);

    int insert(ArticleCmt record);


    ArticleCmtVo selectByPrimaryKey(String ArticleCmtId);


    int updateByPrimaryKey(ArticleCmt record);
    
    List<ArticleCmtVo> findList(@Param("condition") ArticleCmtQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ArticleCmtQVo condition);

	int isExit(ArticleCmt record);

}