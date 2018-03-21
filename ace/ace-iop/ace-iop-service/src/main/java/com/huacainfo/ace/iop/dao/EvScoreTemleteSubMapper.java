package com.huacainfo.ace.iop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.iop.model.EvScoreTemleteSub;
import com.huacainfo.ace.iop.vo.EvScoreTemleteSubQVo;
import com.huacainfo.ace.iop.vo.EvScoreTemleteSubVo;

public interface EvScoreTemleteSubMapper {
    int deleteByPrimaryKey(String EvScoreTemleteSubId);

    int insert(EvScoreTemleteSub record);

    int insertSelective(EvScoreTemleteSub record);

    EvScoreTemleteSubVo selectByPrimaryKey(String EvScoreTemleteSubId);

    int updateByPrimaryKeySelective(EvScoreTemleteSub record);

    int updateByPrimaryKey(EvScoreTemleteSub record);
    
    List<EvScoreTemleteSubVo> findList(@Param("condition") EvScoreTemleteSubQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvScoreTemleteSubQVo condition);

	int isExit(EvScoreTemleteSub record);
}