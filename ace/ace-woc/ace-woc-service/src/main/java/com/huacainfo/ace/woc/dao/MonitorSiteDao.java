package com.huacainfo.ace.woc.dao;

import java.util.List;

import com.huacainfo.ace.woc.model.Device;
import com.huacainfo.ace.woc.vo.DeviceVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.MonitorSite;
import com.huacainfo.ace.woc.vo.MonitorSiteQVo;
import com.huacainfo.ace.woc.vo.MonitorSiteVo;

public interface MonitorSiteDao {
    MonitorSiteVo selectVoByPrimaryKey(String id);

    MonitorSite selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(MonitorSite record);

    int insertSelective(MonitorSite record);

    int updateByPrimaryKey(MonitorSite record);

    int updateByPrimaryKeySelective(MonitorSite record);
    
    List<MonitorSiteVo> findList(@Param("condition") MonitorSiteQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") MonitorSiteQVo condition);

	int isExit(MonitorSite record);

}