package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuProjectApplyRes;
import com.huacainfo.ace.cu.vo.CuProjectApplyResQVo;
import com.huacainfo.ace.cu.vo.CuProjectApplyResVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CuProjectApplyResDao {

    CuProjectApplyRes selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuProjectApplyRes record);

    int insertSelective(CuProjectApplyRes record);

    int updateByPrimaryKey(CuProjectApplyRes record);

    int updateByPrimaryKeySelective(CuProjectApplyRes record);

    CuProjectApplyResVo selectVoByPrimaryKey(String id);

    List<CuProjectApplyResVo> findList(@Param("condition") CuProjectApplyResQVo condition,
                                       @Param("start") int start,
                                       @Param("limit") int limit,
                                       @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuProjectApplyResQVo condition);

    int isExit(CuProjectApplyRes record);

}