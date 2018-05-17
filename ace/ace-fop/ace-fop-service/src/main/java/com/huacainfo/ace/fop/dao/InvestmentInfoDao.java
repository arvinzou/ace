package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.InvestmentInfo;
import com.huacainfo.ace.fop.vo.InvestmentInfoQVo;
import com.huacainfo.ace.fop.vo.InvestmentInfoVo;

public interface InvestmentInfoDao {

    InvestmentInfo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(InvestmentInfo record);

    int insertSelective(InvestmentInfo record);

    int updateByPrimaryKey(InvestmentInfo record);

    int updateByPrimaryKeySelective(InvestmentInfo record);

    InvestmentInfoVo selectVoByPrimaryKey(String id);

    List
            <InvestmentInfoVo> findList(@Param("condition") InvestmentInfoQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") InvestmentInfoQVo condition);

    int isExit(InvestmentInfo record);

}