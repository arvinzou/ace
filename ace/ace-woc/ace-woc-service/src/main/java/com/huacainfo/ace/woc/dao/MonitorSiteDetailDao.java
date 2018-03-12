package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.MonitorSiteDetail;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailQVo;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailVo;

public interface MonitorSiteDetailDao {
    int deleteByPrimaryKey(String MonitorSiteDetailId);

    int insert(MonitorSiteDetail record);


    MonitorSiteDetailVo selectByPrimaryKey(String MonitorSiteDetailId);


    int updateByPrimaryKey(MonitorSiteDetail record);
    
    List<MonitorSiteDetailVo> findList(@Param("condition") MonitorSiteDetailQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") MonitorSiteDetailQVo condition);

	int isExit(MonitorSiteDetail record);

}