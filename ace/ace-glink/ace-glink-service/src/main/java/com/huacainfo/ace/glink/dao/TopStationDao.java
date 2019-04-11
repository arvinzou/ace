package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.TopStation;
import com.huacainfo.ace.glink.vo.TopStationQVo;
import com.huacainfo.ace.glink.vo.TopStationVo;

public interface TopStationDao {

    TopStation selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TopStation record);


    int updateByPrimaryKey(TopStation record);


    TopStationVo selectVoByPrimaryKey(String id);

    List<TopStationVo> findList(@Param("condition") TopStationQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TopStationQVo condition);

    int isExit(TopStation record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);


}
