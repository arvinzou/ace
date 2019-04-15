package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Scheduler;
import com.huacainfo.ace.portal.vo.SchedulerQVo;
import com.huacainfo.ace.portal.vo.SchedulerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchedulerDao {

    Scheduler selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Scheduler record);

    int updateByPrimaryKey(Scheduler record);

    SchedulerVo selectVoByPrimaryKey(String id);

    List<SchedulerVo> findList(@Param("condition") SchedulerQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SchedulerQVo condition);

    int isExist(Scheduler record);

    int updateStatus(@Param("id") String id,
                     @Param("status") String status);

    int updateValidState(@Param("id") String id,
                         @Param("state") String state);
}
