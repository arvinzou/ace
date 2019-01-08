package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.RoadGps;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface RoadGpsDao {


    int insert(RoadGps record);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}