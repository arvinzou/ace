package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SeCustomArea;
import com.huacainfo.ace.glink.vo.SeCustomAreaQVo;
import com.huacainfo.ace.glink.vo.SeCustomAreaVo;

public interface SeCustomAreaDao {

    SeCustomArea selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeCustomArea record);

    int updateByPrimaryKey(SeCustomArea record);

    SeCustomAreaVo selectVoByPrimaryKey(String id);

    List<SeCustomAreaVo> findList(@Param("condition") SeCustomAreaQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeCustomAreaQVo condition);

    int isExit(SeCustomArea record);

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

    int allClear();

}
