package com.huacainfo.ace.gis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GpsChianMapper {
	/*参合资源使用*/
    List<Map<String,Object>> selectListByPAreaName01(String name);
    List<Map<String,Object>> selectListByPAreaName02(String name);
    List<Map<String,Object>> selectListByPAreaName03(String name);
    List<Map<String,Object>> selectListByPAreaName04(String name);
    List<Map<String,Object>> selectListByPAreaName05(String name);
    /*资金运行情况*/
    List<Map<String,Object>> selectListByPAreaName06(String name);
    List<Map<String,Object>> selectListByPAreaName07(String name);
    List<Map<String,Object>> selectListByPAreaName08(String name);
    List<Map<String,Object>> selectListByPAreaName09(String name);
    /*疾病用药地图*/
    List<Map<String,Object>> selectListByPAreaName10(@Param("params") Map<String, Object> params);
    /*疾病用药指标*/
    List<Map<String,Object>> selectListByPAreaName11(@Param("params") Map<String, Object> params);
    
    List<Map<String,Object>> selectListByPAreaName12(@Param("params") Map<String, Object> params);//
    List<Map<String,Object>> selectListByPAreaName13(@Param("params") Map<String, Object> params);//
    List<Map<String,Object>> selectListByPAreaName14(@Param("params") Map<String, Object> params);//
    List<Map<String,Object>> selectListByPAreaName15(@Param("params") Map<String, Object> params);//
    List<Map<String,Object>> selectListAll();
    /*跨区补偿人员查询*/
    List<Map<String,Object>> selectPersonByDateAndAreaCode(@Param("areaCode") String areaCode, @Param("date") String date);
    
    List<Map<String,Object>> selectListByPAreaName16(@Param("params") Map<String, Object> params);//在线人数
    
    List<Map<String, Object>> selectListByPAreaName99(String name);
}