package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SeTimerData;
import com.huacainfo.ace.glink.vo.SeTimerDataQVo;
import com.huacainfo.ace.glink.vo.SeTimerDataVo;

public interface SeTimerDataDao {

    SeTimerData selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeTimerData record);

    int updateByPrimaryKey(SeTimerData record);

    SeTimerDataVo selectVoByPrimaryKey(String id);

    List
            <SeTimerDataVo> findList(@Param("condition") SeTimerDataQVo condition,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeTimerDataQVo condition);

    int isExit(SeTimerData record);

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
