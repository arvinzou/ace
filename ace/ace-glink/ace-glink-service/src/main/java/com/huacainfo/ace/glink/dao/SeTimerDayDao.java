package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SeTimerDay;
import com.huacainfo.ace.glink.vo.SeTimerDayQVo;
import com.huacainfo.ace.glink.vo.SeTimerDayVo;

public interface SeTimerDayDao {

    SeTimerDay selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeTimerDay record);

    int updateByPrimaryKey(SeTimerDay record);

    SeTimerDayVo selectVoByPrimaryKey(String id);

    List
            <SeTimerDayVo> findList(@Param("condition") SeTimerDayQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeTimerDayQVo condition);

    int isExit(SeTimerDay record);

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
