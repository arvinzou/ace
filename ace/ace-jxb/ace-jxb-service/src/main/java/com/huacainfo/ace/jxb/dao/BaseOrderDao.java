package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.vo.BaseOrderQVo;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseOrderDao {

    BaseOrder selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(BaseOrder record);

    int insertSelective(BaseOrder record);

    int updateByPrimaryKey(BaseOrder record);

    int updateByPrimaryKeySelective(BaseOrder record);

    BaseOrderVo selectVoByPrimaryKey(String id);

    List<BaseOrderVo> findList(@Param("condition") BaseOrderQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BaseOrderQVo condition);

    int orderStatistics(@Param("condition") BaseOrder condition);

    int isExit(BaseOrder record);

}