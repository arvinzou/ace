package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SeTimerMonth;
import com.huacainfo.ace.glink.vo.SeTimerMonthQVo;
import com.huacainfo.ace.glink.vo.SeTimerMonthVo;

public interface SeTimerMonthDao {

    SeTimerMonth selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeTimerMonth record);

    int updateByPrimaryKey(SeTimerMonth record);

    SeTimerMonthVo selectVoByPrimaryKey(String id);

    List
            <SeTimerMonthVo> findList(@Param("condition") SeTimerMonthQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeTimerMonthQVo condition);

    int isExit(SeTimerMonth record);

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
