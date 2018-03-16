package com.huacainfo.ace.woc.dao;

import java.util.List;

import com.huacainfo.ace.woc.model.Device;
import com.huacainfo.ace.woc.vo.DeviceVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.Blacklist;
import com.huacainfo.ace.woc.vo.BlacklistQVo;
import com.huacainfo.ace.woc.vo.BlacklistVo;

public interface BlacklistDao {
    Blacklist selectByPrimaryKey(String id);

    BlacklistVo selectVoByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Blacklist record);

    int insertSelective(Blacklist record);

    int updateByPrimaryKey(Blacklist record);

    int updateByPrimaryKeySelective(Blacklist record);

    
    List<BlacklistVo> findList(@Param("condition") BlacklistQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") BlacklistQVo condition);

	int isExit(Blacklist record);

}