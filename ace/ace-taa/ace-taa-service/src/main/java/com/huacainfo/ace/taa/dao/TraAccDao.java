package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.TraAcc;
import com.huacainfo.ace.taa.vo.TraAccQVo;
import com.huacainfo.ace.taa.vo.TraAccVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TraAccDao {

    TraAcc selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TraAcc record);


    int updateByPrimaryKey(TraAcc record);


    TraAccVo selectVoByPrimaryKey(String id);

    List<TraAccVo> findList(@Param("condition") TraAccQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TraAccQVo condition);

    int isExit(TraAcc record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);


    /**
     * 交通事故倒序表
     * <p>路段交通事故次数倒叙表</p>
     * <p>路段交通死亡人数倒叙表</p>
     *
     * @param params  参数
     * @param start   分页1
     * @param limit   分页2
     * @param orderBy 排序规则
     *                ORDER BY v.occurTimes DESC
     *                ORDER BY v.deathNum DESC
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> reverseReport(@Param("params") Map<String, Object> params,
                                            @Param("start") int start,
                                            @Param("limit") int limit,
                                            @Param("orderBy") String orderBy);

    /**
     * 事故死亡人数同期对比 报表
     *
     * @param params 参数
     * @return Map<String,Object>
     */
    Map<String, Object> contrastiveReport(Map<String, String> params);
}