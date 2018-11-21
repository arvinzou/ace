package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.OrderCalculation;
import com.huacainfo.ace.jxb.vo.OrderCalculationQVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderCalculationDao {
    int deleteByPrimaryKey(String id);

    int insert(OrderCalculation record);

    int insertSelective(OrderCalculation record);

    OrderCalculation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderCalculation record);

    int updateByPrimaryKey(OrderCalculation record);

    List<OrderCalculation> findList(@Param("condition") OrderCalculationQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);
}