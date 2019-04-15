package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.AmmeterNode;
import com.huacainfo.ace.glink.vo.AmmeterNodeQVo;
import com.huacainfo.ace.glink.vo.AmmeterNodeVo;

public interface AmmeterNodeDao {

    AmmeterNode selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(AmmeterNode record);

    int updateByPrimaryKey(AmmeterNode record);

    AmmeterNodeVo selectVoByPrimaryKey(String id);

    List<AmmeterNodeVo> findList(@Param("condition") AmmeterNodeQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") AmmeterNodeQVo condition);

    int isExit(AmmeterNode record);


}
