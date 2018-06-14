package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuProject;
import com.huacainfo.ace.cu.vo.CuProjectQVo;
import com.huacainfo.ace.cu.vo.CuProjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CuProjectDao {

    CuProject selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuProject record);

    int insertSelective(CuProject record);

    int updateByPrimaryKey(CuProject record);

    int updateByPrimaryKeySelective(CuProject record);

    CuProjectVo selectVoByPrimaryKey(String id);

    List<CuProjectVo> findList(@Param("condition") CuProjectQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuProjectQVo condition);

    int isExit(CuProject record);

}