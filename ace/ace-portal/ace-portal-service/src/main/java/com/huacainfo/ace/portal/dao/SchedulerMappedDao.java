package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.SchedulerMapped;
import com.huacainfo.ace.portal.vo.SchedulerMappedQVo;
import com.huacainfo.ace.portal.vo.SchedulerMappedVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchedulerMappedDao {

    SchedulerMapped selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SchedulerMapped record);

    int updateByPrimaryKey(SchedulerMapped record);

    List<SchedulerMappedVo> findVoList(@Param("condition") SchedulerMappedQVo condition,
                                       @Param("start") int start,
                                       @Param("limit") int limit,
                                       @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SchedulerMappedQVo condition);
}
