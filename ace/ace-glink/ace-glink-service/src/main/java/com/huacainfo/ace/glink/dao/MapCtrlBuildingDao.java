package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.MapCtrlBuilding;
import com.huacainfo.ace.glink.vo.MapCtrlBuildingQVo;
import com.huacainfo.ace.glink.vo.MapCtrlBuildingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MapCtrlBuildingDao {

    MapCtrlBuilding selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(MapCtrlBuilding record);

    int updateByPrimaryKey(MapCtrlBuilding record);

    MapCtrlBuildingVo selectVoByPrimaryKey(String id);

    List<MapCtrlBuildingVo> findList(@Param("condition") MapCtrlBuildingQVo condition,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findCount(@Param("condition") MapCtrlBuildingQVo condition);

    int isExist(MapCtrlBuilding record);

    int updateByCtrlCode(MapCtrlBuilding o);
}
