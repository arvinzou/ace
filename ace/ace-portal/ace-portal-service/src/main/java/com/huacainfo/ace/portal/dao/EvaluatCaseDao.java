package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.EvaluatCase;
import com.huacainfo.ace.portal.vo.EvaluatCaseQVo;
import com.huacainfo.ace.portal.vo.EvaluatCaseVo;

public interface EvaluatCaseDao {
    int deleteByPrimaryKey(String EvaluatCaseId);

    int insert(EvaluatCase record);


    EvaluatCaseVo selectByPrimaryKey(String EvaluatCaseId);


    int updateByPrimaryKey(EvaluatCase record);
    
    List<EvaluatCaseVo> findList(@Param("condition") EvaluatCaseQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvaluatCaseQVo condition);

    int isExit(EvaluatCase condition);

	List<Map<String,Object>> getList(Map<String,Object> params);

    Map<String,Object> getById(String id);

}