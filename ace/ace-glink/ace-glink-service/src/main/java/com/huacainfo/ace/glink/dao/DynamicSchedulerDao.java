package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.DynamicScheduler;
import com.huacainfo.ace.glink.vo.DynamicSchedulerQVo;
import com.huacainfo.ace.glink.vo.DynamicSchedulerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DynamicSchedulerDao {

    DynamicScheduler selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(DynamicScheduler record);

    int updateByPrimaryKey(DynamicScheduler record);

    DynamicSchedulerVo selectVoByPrimaryKey(String id);

    List<DynamicSchedulerVo> findList(@Param("condition") DynamicSchedulerQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") DynamicSchedulerQVo condition);

    int isExit(DynamicScheduler record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
