package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopLoanProduct;
import com.huacainfo.ace.fop.vo.FopLoanProductQVo;
import com.huacainfo.ace.fop.vo.FopLoanProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopLoanProductDao {

    FopLoanProduct selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopLoanProduct record);

    int insertSelective(FopLoanProduct record);

    int updateByPrimaryKey(FopLoanProduct record);

    int updateByPrimaryKeySelective(FopLoanProduct record);

    FopLoanProductVo selectVoByPrimaryKey(String id);

    List<FopLoanProductVo> findList(@Param("condition") FopLoanProductQVo condition,
                                    @Param("start") int start, @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopLoanProductQVo condition);

    int isExit(FopLoanProduct record);

}