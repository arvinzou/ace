package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.Device;
import com.huacainfo.ace.woc.vo.DeviceQVo;
import com.huacainfo.ace.woc.vo.DeviceVo;

public interface DeviceDao {
    int deleteByPrimaryKey(String DeviceId);

    int insert(Device record);


    DeviceVo selectByPrimaryKey(String DeviceId);


    int updateByPrimaryKey(Device record);
    
    List<DeviceVo> findList(@Param("condition") DeviceQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") DeviceQVo condition);

	int isExit(Device record);

}