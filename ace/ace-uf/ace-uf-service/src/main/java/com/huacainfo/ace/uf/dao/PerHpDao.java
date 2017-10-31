package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.PerHp;
import com.huacainfo.ace.uf.vo.PerHpQVo;
import com.huacainfo.ace.uf.vo.PerHpVo;

public interface PerHpDao {
    int deleteByPrimaryKey(String PerHpId);

    int insert(PerHp record);


    PerHpVo selectByPrimaryKey(String PerHpId);


    int updateByPrimaryKey(PerHp record);
    
    List<PerHpVo> findList(@Param("condition") PerHpQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PerHpQVo condition);

	int isExit(PerHp record);

}