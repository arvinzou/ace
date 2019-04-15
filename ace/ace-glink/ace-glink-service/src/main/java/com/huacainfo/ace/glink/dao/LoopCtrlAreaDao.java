package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.LoopCtrlArea;
import com.huacainfo.ace.glink.vo.LoopCtrlAreaQVo;
import com.huacainfo.ace.glink.vo.LoopCtrlAreaVo;

public interface LoopCtrlAreaDao {

    LoopCtrlArea selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(LoopCtrlArea record);

    int updateByPrimaryKey(LoopCtrlArea record);

    LoopCtrlAreaVo selectVoByPrimaryKey(String id);

    List
            <LoopCtrlAreaVo> findList(@Param("condition") LoopCtrlAreaQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LoopCtrlAreaQVo condition);

    int isExit(LoopCtrlArea record);

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
