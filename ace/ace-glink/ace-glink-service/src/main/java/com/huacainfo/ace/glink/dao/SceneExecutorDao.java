package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SceneExecutor;
import com.huacainfo.ace.glink.vo.SceneExecutorQVo;
import com.huacainfo.ace.glink.vo.SceneExecutorVo;

public interface SceneExecutorDao {

    SceneExecutor selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SceneExecutor record);

    int updateByPrimaryKey(SceneExecutor record);

    SceneExecutorVo selectVoByPrimaryKey(String id);

    List<SceneExecutorVo> findList(@Param("condition") SceneExecutorQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SceneExecutorQVo condition);

    int isExit(SceneExecutor record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);

    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
