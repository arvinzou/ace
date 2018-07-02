package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.portal.model.PageData;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.TplPage;
import com.huacainfo.ace.portal.vo.TplPageQVo;
import com.huacainfo.ace.portal.vo.TplPageVo;

public interface TplPageDao {
    int deleteByPrimaryKey(String TplPageId);

    int insert(TplPage record);


    TplPageVo selectByPrimaryKey(String TplPageId);


    int updateByPrimaryKey(TplPage record);
    
    List<TplPageVo> findList(@Param("condition") TplPageQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TplPageQVo condition);

	int isExit(TplPage record);

    List<Map<String,Object>> getList(Map<String,Object> params);

    Map<String,Object> getById(String id);

    List<PageData> getPageData(String pageId);

    List<Map<String,Object>> getArticleTopListByPageId(String pageId);

    int updateNameById(@Param("id")String id,@Param("name")String name);

    int updateCoverById(@Param("id")String id,@Param("cover")String cover);

}