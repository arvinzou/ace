package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SeRouteState;
import com.huacainfo.ace.glink.vo.SeRouteStateQVo;
import com.huacainfo.ace.glink.vo.SeRouteStateVo;

public interface SeRouteStateDao {

    SeRouteState selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeRouteState record);

    int updateByPrimaryKey(SeRouteState record);

    SeRouteStateVo selectVoByPrimaryKey(String id);

    List<SeRouteStateVo> findList(@Param("condition") SeRouteStateQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeRouteStateQVo condition);

    int isExit(SeRouteState record);

    int updateStatus(@Param("id") String id, @Param("status") String status);

    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);

    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);

    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    int clearAll();
}
