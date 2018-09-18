package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.OrderInfo;
import com.huacainfo.ace.society.vo.OrderInfoQVo;
import com.huacainfo.ace.society.vo.OrderInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoDao {

    OrderInfo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(OrderInfo record);


    int updateByPrimaryKey(OrderInfo record);


    OrderInfoVo selectVoByPrimaryKey(String id);

    List<OrderInfoVo> findList(@Param("condition") OrderInfoQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") OrderInfoQVo condition);

    int isExit(OrderInfo record);

    int updateStatus(OrderInfo record);

}