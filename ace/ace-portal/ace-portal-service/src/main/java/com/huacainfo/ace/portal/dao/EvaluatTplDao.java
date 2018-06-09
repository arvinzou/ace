package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.EvaluatTpl;
import com.huacainfo.ace.portal.vo.EvaluatTplQVo;
import com.huacainfo.ace.portal.vo.EvaluatTplVo;

public interface EvaluatTplDao {
    int deleteByPrimaryKey(String EvaluatTplId);

    int insert(EvaluatTpl record);


    EvaluatTplVo selectByPrimaryKey(String EvaluatTplId);


    int updateByPrimaryKey(EvaluatTpl record);
    
    List<EvaluatTplVo> findList(@Param("condition") EvaluatTplQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvaluatTplQVo condition);

	int isExit(EvaluatTpl record);

	List<Map<String,Object>> getList(Map<String,Object> params);

    Map<String,Object> getById(String id);

}