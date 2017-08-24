package com.huacainfo.ace.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.TemplateCmcc;
import com.huacainfo.ace.portal.vo.TemplateCmccVo;

public interface TemplateCmccMapper {
    int deleteByPrimaryKey(String templateCmccId);

    int insert(TemplateCmcc record);

    int insertSelective(TemplateCmcc record);

    TemplateCmcc selectByPrimaryKey(String templateCmccId);

    int updateByPrimaryKeySelective(TemplateCmcc record);

    int updateByPrimaryKey(TemplateCmcc record);
    
    List<TemplateCmccVo> findList(@Param("condition") TemplateCmcc condition,
                                  @Param("start") int start, @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

	int findCount(@Param("condition") TemplateCmcc condition);


	int isExitByName(@Param("name") String name);
}