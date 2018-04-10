package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.Options;
import com.huacainfo.ace.jxb.vo.OptionsQVo;
import com.huacainfo.ace.jxb.vo.OptionsVo;

public interface OptionsDao {

    OptionsVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Options record);

    int insertSelective(Options record);

    int updateByPrimaryKey(Options record);

    int updateByPrimaryKeySelective(Options record);

    
    List<OptionsVo> findList(@Param("condition") OptionsQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") OptionsQVo condition);

	int isExit(Options record);

}