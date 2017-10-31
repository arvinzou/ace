package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.PerHobby;
import com.huacainfo.ace.uf.vo.PerHobbyQVo;
import com.huacainfo.ace.uf.vo.PerHobbyVo;

public interface PerHobbyDao {
    int deleteByPrimaryKey(String PerHobbyId);

    int insert(PerHobby record);


    PerHobbyVo selectByPrimaryKey(String PerHobbyId);


    int updateByPrimaryKey(PerHobby record);
    
    List<PerHobbyVo> findList(@Param("condition") PerHobbyQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PerHobbyQVo condition);

	int isExit(PerHobby record);

}