package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.MonitorSite;
import com.huacainfo.ace.woc.vo.MonitorSiteQVo;
import com.huacainfo.ace.woc.vo.MonitorSiteVo;

public interface MonitorSiteDao {
    int deleteByPrimaryKey(String MonitorSiteId);

    int insert(MonitorSite record);


    MonitorSiteVo selectByPrimaryKey(String MonitorSiteId);


    int updateByPrimaryKey(MonitorSite record);
    
    List<MonitorSiteVo> findList(@Param("condition") MonitorSiteQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") MonitorSiteQVo condition);

	int isExit(MonitorSite record);

}