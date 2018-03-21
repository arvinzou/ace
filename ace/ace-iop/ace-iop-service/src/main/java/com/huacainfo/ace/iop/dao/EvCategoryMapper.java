package com.huacainfo.ace.iop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.iop.model.EvCategory;
import com.huacainfo.ace.iop.vo.EvCategoryQVo;
import com.huacainfo.ace.iop.vo.EvCategoryVo;

public interface EvCategoryMapper {
    int deleteByPrimaryKey(String EvCategoryId);

    int insert(EvCategory record);

    int insertSelective(EvCategory record);

    EvCategoryVo selectByPrimaryKey(String EvCategoryId);

    int updateByPrimaryKeySelective(EvCategory record);

    int updateByPrimaryKey(EvCategory record);
    
    List<EvCategoryVo> findList(@Param("condition") EvCategoryQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvCategoryQVo condition);

	int isExit(EvCategory record);
}