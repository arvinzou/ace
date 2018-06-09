package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.EvaluatCaseSub;
import com.huacainfo.ace.portal.vo.EvaluatCaseSubQVo;
import com.huacainfo.ace.portal.vo.EvaluatCaseSubVo;

public interface EvaluatCaseSubDao {
    int deleteByPrimaryKey(String EvaluatCaseSubId);

    int insert(EvaluatCaseSub record);


    EvaluatCaseSubVo selectByPrimaryKey(String EvaluatCaseSubId);


    int updateByPrimaryKey(EvaluatCaseSub record);
    
    List<EvaluatCaseSubVo> findList(@Param("condition") EvaluatCaseSubQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvaluatCaseSubQVo condition);

	int isExit(EvaluatCaseSub record);

	List<Map<String,Object>> getList(Map<String,Object> params);

    Map<String,Object> getById(String id);

}