package com.huacainfo.ace.iop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.iop.model.EvTemplete;
import com.huacainfo.ace.iop.vo.EvTempleteQVo;
import com.huacainfo.ace.iop.vo.EvTempleteVo;

public interface EvTempleteMapper {
    int deleteByPrimaryKey(String EvTempleteId);

    int insert(EvTemplete record);

    int insertSelective(EvTemplete record);

    EvTempleteVo selectByPrimaryKey(String EvTempleteId);

    int updateByPrimaryKeySelective(EvTemplete record);

    int updateByPrimaryKey(EvTemplete record);

    List<EvTempleteVo> findList(@Param("condition") EvTempleteQVo condition,
                                @Param("start") int start, @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") EvTempleteQVo condition);

    int isExit(EvTemplete record);
}