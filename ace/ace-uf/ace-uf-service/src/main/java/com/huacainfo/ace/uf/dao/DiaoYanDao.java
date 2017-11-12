package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.DiaoYan;
import com.huacainfo.ace.uf.vo.DiaoYanQVo;
import com.huacainfo.ace.uf.vo.DiaoYanVo;

public interface DiaoYanDao {
    int deleteByPrimaryKey(String DiaoYanId);

    int insert(DiaoYan record);


    DiaoYanVo selectByPrimaryKey(String DiaoYanId);


    int updateByPrimaryKey(DiaoYan record);
    
    List<DiaoYanVo> findList(@Param("condition") DiaoYanQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") DiaoYanQVo condition);

	int isExit(DiaoYan record);

}