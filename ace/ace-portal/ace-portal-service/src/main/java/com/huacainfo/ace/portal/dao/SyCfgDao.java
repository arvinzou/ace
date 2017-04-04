package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.SyCfg;
import com.huacainfo.ace.portal.vo.SyCfgQVo;
import com.huacainfo.ace.portal.vo.SyCfgVo;

public interface SyCfgDao {
	int deleteByPrimaryKey(String SyCfgId);

	int insert(SyCfg record);

	SyCfg selectByPrimaryKey(String SyCfgId);

	int updateByPrimaryKey(SyCfg record);

	List<SyCfgVo> findList(@Param("condition") SyCfgQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") SyCfgQVo condition);

	int isExit(SyCfg record);

	List<Map<String,Object>> selectSyCfgByUser(@Param("syid")String[] syid);
}