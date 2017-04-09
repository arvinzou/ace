package com.huacainfo.ace.weui.dao.report;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReportDao {
	int updateReading(@Param("condition") Map<String, Object> condition);
	List<Map<String,Object>> query(@Param("condition") Map<String, Object> condition,@Param("start") int start,@Param("limit") int limit);
}
