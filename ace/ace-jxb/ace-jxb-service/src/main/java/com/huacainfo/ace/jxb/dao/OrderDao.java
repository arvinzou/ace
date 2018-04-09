package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.Order;
import com.huacainfo.ace.jxb.vo.OrderQVo;
import com.huacainfo.ace.jxb.vo.OrderVo;

public interface OrderDao {

    OrderVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    int updateByPrimaryKey(Order record);

    int updateByPrimaryKeySelective(Order record);

    
    List<OrderVo> findList(@Param("condition") OrderQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") OrderQVo condition);

	int isExit(Order record);

}