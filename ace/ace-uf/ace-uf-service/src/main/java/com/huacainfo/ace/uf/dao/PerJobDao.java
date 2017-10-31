package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.PerJob;
import com.huacainfo.ace.uf.vo.PerJobQVo;
import com.huacainfo.ace.uf.vo.PerJobVo;

public interface PerJobDao {
    int deleteByPrimaryKey(String PerJobId);

    int insert(PerJob record);


    PerJobVo selectByPrimaryKey(String PerJobId);


    int updateByPrimaryKey(PerJob record);
    
    List<PerJobVo> findList(@Param("condition") PerJobQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PerJobQVo condition);

	int isExit(PerJob record);

}