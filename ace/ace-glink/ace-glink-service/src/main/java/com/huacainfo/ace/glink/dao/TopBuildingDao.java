package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.TopBuilding;
import com.huacainfo.ace.glink.vo.TopBuildingQVo;
import com.huacainfo.ace.glink.vo.TopBuildingVo;

public interface TopBuildingDao {

    TopBuilding selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TopBuilding record);


    int updateByPrimaryKey(TopBuilding record);


    TopBuildingVo selectVoByPrimaryKey(String id);

    List<TopBuildingVo> findList(@Param("condition") TopBuildingQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TopBuildingQVo condition);

    int isExit(TopBuilding record);

    int updateStatus(@Param("id") String id, @Param("status") String status);
}

