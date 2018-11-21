package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Cases;
import com.huacainfo.ace.woc.vo.CasesQVo;
import com.huacainfo.ace.woc.vo.CasesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CasesDao {

    Cases selectByPrimaryKey(String id);

    CasesVo selectVoByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Cases record);

    int insertSelective(Cases record);

    int updateByPrimaryKey(Cases record);

    int updateByPrimaryKeySelective(Cases record);


    List<CasesVo> findList(@Param("condition") CasesQVo condition,
                          @Param("start") int start, @Param("limit") int limit,
                          @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CasesQVo condition);

    int isExit(Cases record);

    Map<String, Object> selectStatistics(@Param("params") Map<String, Object> params);
}