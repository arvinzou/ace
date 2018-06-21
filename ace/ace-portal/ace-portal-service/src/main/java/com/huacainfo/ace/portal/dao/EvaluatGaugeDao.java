package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.EvaluatGauge;
import com.huacainfo.ace.portal.vo.EvaluatGaugeQVo;
import com.huacainfo.ace.portal.vo.EvaluatGaugeVo;

public interface EvaluatGaugeDao {
    int deleteByPrimaryKey(String EvaluatGaugeId);

    int deleteByEvaluatTplId(String evaluatTplId);

    int insertSelective(EvaluatGauge record);


    EvaluatGaugeVo selectByPrimaryKey(String EvaluatGaugeId);


    int updateByPrimaryKey(EvaluatGauge record);
    
    List<EvaluatGaugeVo> findList(@Param("condition") EvaluatGaugeQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

    EvaluatGauge getEvaluation(@Param("condition") EvaluatGaugeQVo condition);

    List<EvaluatGauge> findLists(@Param("evaluatTplId") String evaluatTplId);

	int findCount(@Param("condition") EvaluatGaugeQVo condition);

	int isExit(EvaluatGauge record);

	List<Map<String,Object>> getList(Map<String,Object> params);

    Map<String,Object> getById(String id);

}