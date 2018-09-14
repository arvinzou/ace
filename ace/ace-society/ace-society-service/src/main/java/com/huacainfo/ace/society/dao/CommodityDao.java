package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.Commodity;
import com.huacainfo.ace.society.vo.CommodityQVo;
import com.huacainfo.ace.society.vo.CommodityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityDao {

    Commodity selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    int updateByPrimaryKey(Commodity record);

    int updateByPrimaryKeySelective(Commodity record);

    CommodityVo selectVoByPrimaryKey(String id);

    List<CommodityVo> findList(@Param("condition") CommodityQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CommodityQVo condition);

    int isExit(Commodity record);

}