package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.EvaluatType;
import com.huacainfo.ace.portal.vo.EvaluatTypeQVo;
import com.huacainfo.ace.portal.vo.EvaluatTypeVo;

public interface EvaluatTypeDao {
    int deleteByPrimaryKey(String EvaluatTypeId);

    int insert(EvaluatType record);


    EvaluatTypeVo selectByPrimaryKey(String EvaluatTypeId);


    int updateByPrimaryKey(EvaluatType record);
    
    List<EvaluatTypeVo> findList(@Param("condition") EvaluatTypeQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvaluatTypeQVo condition);

	int isExit(EvaluatType record);

	List<Map<String,Object>> getList(Map<String,Object> params);

    Map<String,Object> getById(String id);

}