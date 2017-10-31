package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.PerCategory;
import com.huacainfo.ace.uf.vo.PerCategoryQVo;
import com.huacainfo.ace.uf.vo.PerCategoryVo;

public interface PerCategoryDao {
    int deleteByPrimaryKey(String PerCategoryId);

    int insert(PerCategory record);


    PerCategoryVo selectByPrimaryKey(String PerCategoryId);


    int updateByPrimaryKey(PerCategory record);
    
    List<PerCategoryVo> findList(@Param("condition") PerCategoryQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PerCategoryQVo condition);

	int isExit(PerCategory record);

}