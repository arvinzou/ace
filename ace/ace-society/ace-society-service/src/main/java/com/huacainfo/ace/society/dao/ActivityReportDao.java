package com.huacainfo.ace.society.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.society.model.ActivityReport;
import com.huacainfo.ace.society.vo.ActivityReportQVo;
import com.huacainfo.ace.society.vo.ActivityReportVo;

public interface ActivityReportDao {

    ActivityReport selectByPrimaryKey(String id);
    ActivityReport selectByActivityId(String activityId);

    int deleteByPrimaryKey(String id);

    int insert(ActivityReport record);

    int insertSelective(ActivityReport record);

    int updateByPrimaryKey(ActivityReport record);

    int updateByPrimaryKeySelective(ActivityReport record);

    ActivityReportVo selectVoByPrimaryKey(String id);

    List<ActivityReportVo> findList(@Param("condition") ActivityReportQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);


    List<ActivityReportVo> adminFindList(@Param("condition") ActivityReportQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActivityReportQVo condition);
    int adminFindCount(@Param("condition") ActivityReportQVo condition);

    int isExit(ActivityReport record);

}