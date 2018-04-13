package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Vehicle;
import com.huacainfo.ace.woc.vo.VehicleQVo;
import com.huacainfo.ace.woc.vo.VehicleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VehicleDao {
    int deleteByPrimaryKey(String id);

//    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    VehicleVo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
    List<VehicleVo> findList(@Param("condition") VehicleQVo condition,
                            @Param("start") int start, @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") VehicleQVo condition);

    int isExit(Vehicle record);

    List<VehicleVo> selectListByKeyWord(@Param("params") Map<String, Object> params);

    /***
     *
     * @return
     */
    List<Vehicle> selectAll();
}