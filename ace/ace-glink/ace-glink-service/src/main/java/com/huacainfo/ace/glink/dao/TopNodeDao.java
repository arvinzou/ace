package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.TopNode;
import com.huacainfo.ace.glink.vo.TopNodeQVo;
import com.huacainfo.ace.glink.vo.TopNodeVo;

public interface TopNodeDao {

TopNode selectByPrimaryKey(String id);

int deleteByPrimaryKey(String id);

int insert(TopNode record);


int updateByPrimaryKey(TopNode record);


TopNodeVo selectVoByPrimaryKey(String id);

List
<TopNodeVo> findList(@Param("condition") TopNodeQVo condition,
    @Param("start") int start,
    @Param("limit") int limit,
    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TopNodeQVo condition);

    int isExit(TopNode record);

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
