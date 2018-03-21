package com.huacainfo.ace.iop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.iop.model.EvScoreTemlete;
import com.huacainfo.ace.iop.vo.EvScoreTemleteQVo;
import com.huacainfo.ace.iop.vo.EvScoreTemleteVo;

public interface EvScoreTemleteMapper {
    int deleteByPrimaryKey(String EvScoreTemleteId);

    int insert(EvScoreTemlete record);

    int insertSelective(EvScoreTemlete record);

    EvScoreTemleteVo selectByPrimaryKey(String EvScoreTemleteId);

    int updateByPrimaryKeySelective(EvScoreTemlete record);

    int updateByPrimaryKey(EvScoreTemlete record);
    
    List<EvScoreTemleteVo> findList(@Param("condition") EvScoreTemleteQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvScoreTemleteQVo condition);

	int isExit(EvScoreTemlete record);
	
	List<EvScoreTemlete> selectListAll();
}