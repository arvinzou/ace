package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.LeBrokenLamp;
import com.huacainfo.ace.glink.vo.LeBrokenLampQVo;
import com.huacainfo.ace.glink.vo.LeBrokenLampVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LeBrokenLampDao {

    LeBrokenLamp selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(LeBrokenLamp record);

    int updateByPrimaryKey(LeBrokenLamp record);

    LeBrokenLampVo selectVoByPrimaryKey(String id);

    List<LeBrokenLampVo> findList(@Param("condition") LeBrokenLampQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LeBrokenLampQVo condition);

    int isExit(LeBrokenLamp record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    int deleteByDate(String date);
}
