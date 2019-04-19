package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SeTimerWeek;
import com.huacainfo.ace.glink.vo.SeTimerWeekQVo;
import com.huacainfo.ace.glink.vo.SeTimerWeekVo;

public interface SeTimerWeekDao {

    SeTimerWeek selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeTimerWeek record);

    int updateByPrimaryKey(SeTimerWeek record);

    SeTimerWeekVo selectVoByPrimaryKey(String id);

    List
            <SeTimerWeekVo> findList(@Param("condition") SeTimerWeekQVo condition,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeTimerWeekQVo condition);

    int isExit(SeTimerWeek record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List
            <Map
                    <String
                            , Object>> getList(@Param("p") Map
            <String
                    , Object> p);


    List
            <Map
                    <String
                            , Object>> getListByCondition(@Param("params") Map
            <String
                    , Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}
