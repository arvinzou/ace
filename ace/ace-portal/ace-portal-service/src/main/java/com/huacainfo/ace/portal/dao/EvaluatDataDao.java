package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.EvaluatData;
import com.huacainfo.ace.portal.vo.EvaluatDataQVo;
import com.huacainfo.ace.portal.vo.EvaluatDataVo;

public interface EvaluatDataDao {
    int deleteByPrimaryKey(String EvaluatDataId);

    int deleteByEvaluatTplId(String evaluatTplId);

    int insertSelective(EvaluatData record);


    EvaluatDataVo selectByPrimaryKey(String EvaluatDataId);


    int updateByPrimaryKeySelective(EvaluatData record);
    
    List<EvaluatDataVo> findList(@Param("condition") EvaluatDataQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvaluatDataQVo condition);

	int isExit(EvaluatData record);

	List<Map<String,Object>> getList(Map<String,Object> params);

    Map<String,Object> getById(String id);

}