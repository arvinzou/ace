package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.EvaluatCmt;
import com.huacainfo.ace.portal.vo.EvaluatCmtQVo;
import com.huacainfo.ace.portal.vo.EvaluatCmtVo;

public interface EvaluatCmtDao {
    int deleteByPrimaryKey(String EvaluatCmtId);

    int insert(EvaluatCmt record);


    EvaluatCmtVo selectByPrimaryKey(String EvaluatCmtId);


    int updateByPrimaryKey(EvaluatCmt record);

    List<EvaluatCmtVo> findList(@Param("condition") EvaluatCmtQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvaluatCmtQVo condition);

	int isExit(EvaluatCmt record);

	List<Map<String,Object>> getList(Map<String,Object> params);

    Map<String,Object> getById(String id);

}