package com.huacainfo.ace.taa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.taa.model.RoadSection;
import com.huacainfo.ace.taa.vo.RoadSectionQVo;
import com.huacainfo.ace.taa.vo.RoadSectionVo;

public interface RoadSectionDao {

    RoadSection selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(RoadSection record);


    int updateByPrimaryKey(RoadSection record);


    RoadSectionVo selectVoByPrimaryKey(String id);

    List<RoadSectionVo> findList(@Param("condition") RoadSectionQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") RoadSectionQVo condition);

    int isExit(RoadSection record);

    int updateStatus(RoadSection record);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    int selectSectionCount(String id);


    int updateSectionCount( @Param("id") String id, @Param("num") int num);


    int updateByName(RoadSection record);

}