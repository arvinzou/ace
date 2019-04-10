package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.TopDevice;
import com.huacainfo.ace.glink.vo.TopDeviceQVo;
import com.huacainfo.ace.glink.vo.TopDeviceVo;

public interface TopDeviceDao {

    TopDevice selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TopDevice record);

    int updateByPrimaryKey(TopDevice record);

    TopDeviceVo selectVoByPrimaryKey(String id);

    List
            <TopDeviceVo> findList(@Param("condition") TopDeviceQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TopDeviceQVo condition);

    int isExit(TopDevice record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
