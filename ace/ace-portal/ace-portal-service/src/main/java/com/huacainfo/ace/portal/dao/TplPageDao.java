package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

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

}