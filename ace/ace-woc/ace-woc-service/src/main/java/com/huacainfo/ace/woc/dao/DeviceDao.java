package com.huacainfo.ace.woc.dao;

import java.util.List;

import com.huacainfo.ace.woc.model.Road;
import com.huacainfo.ace.woc.vo.RoadVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.Device;
import com.huacainfo.ace.woc.vo.DeviceQVo;
import com.huacainfo.ace.woc.vo.DeviceVo;

public interface DeviceDao {

    DeviceVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Device record);

    int insertSelective(Device record);

    int updateByPrimaryKey(Device record);

    int updateByPrimaryKeySelective(Device record);

    List<DeviceVo> findList(@Param("condition") DeviceQVo condition,
                            @Param("start") int start, @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") DeviceQVo condition);

    int isExit(Device record);

}