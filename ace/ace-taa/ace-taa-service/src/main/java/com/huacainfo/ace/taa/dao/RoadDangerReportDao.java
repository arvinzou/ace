package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.RoadDangerReport;
import com.huacainfo.ace.taa.vo.RoadDangerReportQVo;
import com.huacainfo.ace.taa.vo.RoadDangerReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoadDangerReportDao {
    int deleteByPrimaryKey(String id);

    int insert(RoadDangerReport record);

    //int insertSelective(RoadDangerReport record);

    RoadDangerReport selectByPrimaryKey(String id);

    // int updateByPrimaryKeySelective(RoadDangerReport record);

    int updateByPrimaryKeyWithBLOBs(RoadDangerReport record);

    int updateByPrimaryKey(RoadDangerReport record);

    List<RoadDangerReportVo> findList(@Param("condition") RoadDangerReportQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") RoadDangerReportQVo condition);

    RoadDangerReportVo selectVoByPrimaryKey(String id);

    int isExit(RoadDangerReport record);

    int updateStatus(RoadDangerReport record);

    int updateStatus(@Param("id") String Id,
                     @Param("status") String status);


    int updateReason(@Param("id") String Id,
                     @Param("status") String status, @Param("reason") String reason);


    /**
     * 获取用户所有权限
     *
     * @param
     * @return
     */
    List<Map<String, Object>> selectUserRole(String userId);

    /**
     * 获取用户是否有路况权限
     *
     * @param
     * @return
     */
    List<Map<String, Object>> selectUserRoadRole(String userId);
}