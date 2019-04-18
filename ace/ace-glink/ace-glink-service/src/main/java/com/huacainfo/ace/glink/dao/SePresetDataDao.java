package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SePresetData;
import com.huacainfo.ace.glink.vo.SePresetDataQVo;
import com.huacainfo.ace.glink.vo.SePresetDataVo;

public interface SePresetDataDao {

SePresetData selectByPrimaryKey(String id);

int deleteByPrimaryKey(String id);

int insert(SePresetData record);

int updateByPrimaryKey(SePresetData record);

SePresetDataVo selectVoByPrimaryKey(String id);

List
<SePresetDataVo> findList(@Param("condition") SePresetDataQVo condition,
    @Param("start") int start,
    @Param("limit") int limit,
    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SePresetDataQVo condition);

    int isExit(SePresetData record);

    int updateStatus(@Param("id") String id,@Param("status") String status);


    List
    <Map
    <String
    ,Object>> getList(@Param("p")Map
    <String
    ,Object> p);


    List
    <Map
    <String
    , Object>> getListByCondition(@Param("params") Map
    <String
    , Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    }
