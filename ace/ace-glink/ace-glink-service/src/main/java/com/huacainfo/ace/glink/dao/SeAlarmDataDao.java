package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SeAlarmData;
import com.huacainfo.ace.glink.vo.SeAlarmDataQVo;
import com.huacainfo.ace.glink.vo.SeAlarmDataVo;

public interface SeAlarmDataDao {

    SeAlarmData selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeAlarmData record);

    int updateByPrimaryKey(SeAlarmData record);

    SeAlarmDataVo selectVoByPrimaryKey(String id);

    List<SeAlarmDataVo> findList(@Param("condition") SeAlarmDataQVo condition,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeAlarmDataQVo condition);

    int isExit(SeAlarmData record);

    int deleteByPrimaryKeys(@Param("list") List<String> list);

}
