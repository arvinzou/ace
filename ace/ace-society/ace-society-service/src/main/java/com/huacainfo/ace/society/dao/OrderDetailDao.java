package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.OrderDetail;
import com.huacainfo.ace.society.vo.OrderDetailQVo;
import com.huacainfo.ace.society.vo.OrderDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailDao {

    OrderDetail selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    OrderDetailVo selectVoByPrimaryKey(String id);

    List<OrderDetailVo> findList(@Param("condition") OrderDetailQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") OrderDetailQVo condition);

    int isExit(OrderDetail record);

    int updateStatus(OrderDetail record);

}