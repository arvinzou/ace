package com.huacainfo.ace.woc.dao;

import java.util.List;

import com.huacainfo.ace.woc.model.Device;
import com.huacainfo.ace.woc.vo.DeviceVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.MonitorSiteDetail;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailQVo;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailVo;

public interface MonitorSiteDetailDao {

	MonitorSiteDetailVo selectByPrimaryKey(String id);

	int deleteByPrimaryKey(String id);

	int insert(MonitorSiteDetail record);

	int insertSelective(MonitorSiteDetail record);

	int updateByPrimaryKey(MonitorSiteDetail record);

	int updateByPrimaryKeySelective(MonitorSiteDetail record);


    List<MonitorSiteDetailVo> findList(@Param("condition") MonitorSiteDetailQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") MonitorSiteDetailQVo condition);

	int isExit(MonitorSiteDetail record);

}