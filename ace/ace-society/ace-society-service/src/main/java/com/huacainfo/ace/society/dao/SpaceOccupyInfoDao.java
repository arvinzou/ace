package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.SpaceOccupyInfo;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoQVo;
import com.huacainfo.ace.society.vo.SpaceOccupyInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpaceOccupyInfoDao {

    SpaceOccupyInfo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SpaceOccupyInfo record);

    int updateByPrimaryKey(SpaceOccupyInfo record);

    SpaceOccupyInfoVo selectVoByPrimaryKey(String id);

    List<SpaceOccupyInfoVo> findList(@Param("condition") SpaceOccupyInfoQVo condition,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SpaceOccupyInfoQVo condition);

    int isExist(SpaceOccupyInfo record);


//    int updateStatus(SpaceOccupyInfo record);

}