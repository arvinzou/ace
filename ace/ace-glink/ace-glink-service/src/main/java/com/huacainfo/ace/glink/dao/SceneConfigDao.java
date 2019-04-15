package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SceneConfig;
import com.huacainfo.ace.glink.vo.SceneConfigQVo;
import com.huacainfo.ace.glink.vo.SceneConfigVo;

public interface SceneConfigDao {

    SceneConfig selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SceneConfig record);

    int updateByPrimaryKey(SceneConfig record);

    SceneConfigVo selectByPrimaryKeyVo(String id);

    List<SceneConfigVo> findList(@Param("condition") SceneConfigQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SceneConfigQVo condition);

    int isExit(SceneConfig record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
