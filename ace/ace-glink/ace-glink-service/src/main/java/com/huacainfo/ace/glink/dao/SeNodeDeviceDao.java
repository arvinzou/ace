package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNodeDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeNodeDeviceDao {
    int deleteByPrimaryKey(String id);

    int insert(SeNodeDevice record);

    SeNodeDevice selectByPrimaryKey(String id);

    int updateByPrimaryKey(SeNodeDevice record);

    List<SeNodeDevice> findList(@Param("condition") SeNodeDevice condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);
}
