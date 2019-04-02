package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.RoadGps;
import com.huacainfo.ace.taa.vo.RoadGpsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface RoadGpsDao {


    int insert(RoadGps record);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    List<RoadGpsVo> getAroundList(Map<String, Object> p);

    int deleteBySectionId(String sectionId);


    int findCount(@Param("sectionIds") String[] sectionIds);

    RoadGpsVo getUndefined();
}
