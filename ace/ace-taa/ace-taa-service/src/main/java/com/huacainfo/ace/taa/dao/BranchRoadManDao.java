package com.huacainfo.ace.taa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.taa.model.BranchRoadMan;
import com.huacainfo.ace.taa.vo.BranchRoadManQVo;
import com.huacainfo.ace.taa.vo.BranchRoadManVo;

public interface BranchRoadManDao {

    BranchRoadMan selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(BranchRoadMan record);


    int updateByPrimaryKey(BranchRoadMan record);


    BranchRoadManVo selectVoByPrimaryKey(String id);

    List<BranchRoadManVo> findList(@Param("condition") BranchRoadManQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BranchRoadManQVo condition);

    int isExit(BranchRoadMan record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("roadSectionId") String roadSectionId);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}