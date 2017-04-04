package com.huacainfo.ace.kernel.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.kernel.model.Writing;
import com.huacainfo.ace.kernel.vo.WritingQVo;
import com.huacainfo.ace.kernel.vo.WritingVo;

public interface WritingDao {
    int deleteByPrimaryKey(String WritingId);

    int insert(Writing record);


    Writing selectByPrimaryKey(String WritingId);


    int updateByPrimaryKey(Writing record);
    
    List<WritingVo> findList(@Param("condition") WritingQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") WritingQVo condition);

	int isExit(Writing record);

}