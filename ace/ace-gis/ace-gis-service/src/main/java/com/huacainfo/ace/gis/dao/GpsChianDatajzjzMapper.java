package com.huacainfo.ace.gis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GpsChianDatajzjzMapper {
	/* 精准救助地图数据 */
	List<Map<String, Object>> selectListByPAreaName01(String name);

	/* 精准救助对象滚动表格 */
	List<Map<String, Object>> selectListByPAreaName12(
            @Param("params") Map<String, Object> params);

	/* 精准救助资金占比 */
	List<Map<String, Object>> selectListByPAreaName05(String name);

	/* 精准救助对象情况 */
	List<Map<String, Object>> selectListByPAreaName03(String name);

	/* 精准救助补偿趋势 */
	List<Map<String, Object>> selectListByPAreaName14(
            @Param("params") Map<String, Object> params);

	List<Map<String, Object>> selectListAll();

	List<Map<String, Object>> selectPersonByDateAndAreaCode(
            @Param("areaCode") String areaCode, @Param("date") String date);
}