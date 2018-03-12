package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.Blacklist;
import com.huacainfo.ace.woc.vo.BlacklistQVo;
import com.huacainfo.ace.woc.vo.BlacklistVo;

public interface BlacklistDao {
    int deleteByPrimaryKey(String BlacklistId);

    int insert(Blacklist record);


    BlacklistVo selectByPrimaryKey(String BlacklistId);


    int updateByPrimaryKey(Blacklist record);
    
    List<BlacklistVo> findList(@Param("condition") BlacklistQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") BlacklistQVo condition);

	int isExit(Blacklist record);

}