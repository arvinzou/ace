package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuDonateList;
import com.huacainfo.ace.cu.vo.CuDonateListQVo;
import com.huacainfo.ace.cu.vo.CuDonateListVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CuDonateListDao {

    CuDonateList selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuDonateList record);

    int insertSelective(CuDonateList record);

    int updateByPrimaryKey(CuDonateList record);

    int updateByPrimaryKeySelective(CuDonateList record);

    CuDonateListVo selectVoByPrimaryKey(String id);

    List<CuDonateListVo> findList(@Param("condition") CuDonateListQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuDonateListQVo condition);

    int isExit(CuDonateList record);

    BigDecimal getAccDonateAmount(String openId);

    int getAccDonateCount(String openId);


}