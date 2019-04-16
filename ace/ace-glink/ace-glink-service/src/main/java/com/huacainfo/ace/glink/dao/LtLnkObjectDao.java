package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.LtLnkObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LtLnkObjectDao {

    LtLnkObject selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(LtLnkObject record);

    int updateByPrimaryKey(LtLnkObject record);

    LtLnkObject selectVoByPrimaryKey(String id);

    List<LtLnkObject> findList(@Param("condition") LtLnkObject condition,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LtLnkObject condition);

    int isExit(LtLnkObject record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
