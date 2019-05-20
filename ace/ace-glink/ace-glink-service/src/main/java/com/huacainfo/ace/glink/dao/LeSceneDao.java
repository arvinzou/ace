package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.LeScene;
import com.huacainfo.ace.glink.vo.LeSceneQVo;
import com.huacainfo.ace.glink.vo.LeSceneVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LeSceneDao {

    LeScene selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(LeScene record);

    int updateByPrimaryKey(LeScene record);

    LeSceneVo selectVoByPrimaryKey(String id);

    List<LeSceneVo> findList(@Param("condition") LeSceneQVo condition,
                             @Param("start") int start,
                             @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LeSceneQVo condition);

    int isExist(LeScene record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    int clearAll();

    LeScene findBySceneNum(String sceneNum);
}
