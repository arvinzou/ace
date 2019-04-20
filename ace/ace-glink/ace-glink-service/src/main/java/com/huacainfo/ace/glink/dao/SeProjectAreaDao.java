package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SeProjectArea;
import com.huacainfo.ace.glink.vo.SeProjectAreaQVo;
import com.huacainfo.ace.glink.vo.SeProjectAreaVo;

public interface SeProjectAreaDao {

    SeProjectArea selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeProjectArea record);

    int updateByPrimaryKey(SeProjectArea record);

    SeProjectAreaVo selectVoByPrimaryKey(String id);

    List
            <SeProjectAreaVo> findList(@Param("condition") SeProjectAreaQVo condition,
                                       @Param("start") int start,
                                       @Param("limit") int limit,
                                       @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeProjectAreaQVo condition);

    int isExit(SeProjectArea record);

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

    List<Map<String, Object>> selectTreeList();

    int allClear();
}
