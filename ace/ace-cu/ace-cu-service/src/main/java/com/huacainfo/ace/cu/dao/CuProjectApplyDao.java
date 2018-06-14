package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuProjectApply;
import com.huacainfo.ace.cu.vo.CuProjectApplyQVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CuProjectApplyDao {

    CuProjectApply selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuProjectApply record);

    int insertSelective(CuProjectApply record);

    int updateByPrimaryKey(CuProjectApply record);

    int updateByPrimaryKeySelective(CuProjectApply record);

    CuProjectApplyVo selectVoByPrimaryKey(String id);

    List<CuProjectApplyVo> findList(@Param("condition") CuProjectApplyQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuProjectApplyQVo condition);

    int isExit(CuProjectApply record);

}