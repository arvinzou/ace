package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.PerDept;
import com.huacainfo.ace.uf.vo.PerDeptQVo;
import com.huacainfo.ace.uf.vo.PerDeptVo;

public interface PerDeptDao {
    int deleteByPrimaryKey(String PerDeptId);

    int insert(PerDept record);


    PerDeptVo selectByPrimaryKey(String PerDeptId);


    int updateByPrimaryKey(PerDept record);
    
    List<PerDeptVo> findList(@Param("condition") PerDeptQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PerDeptQVo condition);

	int isExit(PerDept record);

}