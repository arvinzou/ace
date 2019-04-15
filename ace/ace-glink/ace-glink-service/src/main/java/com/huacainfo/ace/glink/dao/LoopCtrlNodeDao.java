package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.LoopCtrlNode;
import com.huacainfo.ace.glink.vo.LoopCtrlNodeQVo;
import com.huacainfo.ace.glink.vo.LoopCtrlNodeVo;

public interface LoopCtrlNodeDao {

    LoopCtrlNode selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(LoopCtrlNode record);

    int updateByPrimaryKey(LoopCtrlNode record);

    LoopCtrlNodeVo selectVoByPrimaryKey(String id);

    List
            <LoopCtrlNodeVo> findList(@Param("condition") LoopCtrlNodeQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LoopCtrlNodeQVo condition);

    int isExit(LoopCtrlNode record);

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

    int updateState(@Param("id") String id, @Param("state") String state);

}
