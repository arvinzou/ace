package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuDonateOrder;
import com.huacainfo.ace.cu.vo.CuDonateOrderQVo;
import com.huacainfo.ace.cu.vo.CuDonateOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CuDonateOrderDao {

    CuDonateOrder selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuDonateOrder record);

    int insertSelective(CuDonateOrder record);

    int updateByPrimaryKey(CuDonateOrder record);

    int updateByPrimaryKeySelective(CuDonateOrder record);

    CuDonateOrderVo selectVoByPrimaryKey(String id);

    List<CuDonateOrderVo> findList(@Param("condition") CuDonateOrderQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuDonateOrderQVo condition);

    int isExit(CuDonateOrder record);

}