package com.huacainfo.ace.taa.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.taa.model.TraAcc;
import com.huacainfo.ace.taa.vo.TraAccQVo;
import com.huacainfo.ace.taa.vo.TraAccVo;

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

    int updateStatus(TraAcc record);


    List<Map<String,Object>> getList(@Param("p")Map<String,Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

 }