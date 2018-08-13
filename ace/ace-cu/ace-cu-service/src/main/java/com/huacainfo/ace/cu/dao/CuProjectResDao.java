package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuProjectRes;
import com.huacainfo.ace.cu.vo.CuProjectResQVo;
import com.huacainfo.ace.cu.vo.CuProjectResVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CuProjectResDao {

    CuProjectRes selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuProjectRes record);

    int insertSelective(CuProjectRes record);

    int updateByPrimaryKey(CuProjectRes record);

    int updateByPrimaryKeySelective(CuProjectRes record);

    CuProjectResVo selectVoByPrimaryKey(String id);

    List<CuProjectResVo> findList(@Param("condition") CuProjectResQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuProjectResQVo condition);

    int isExit(CuProjectRes record);

    List<CuProjectRes> findByProjectId(@Param("projectId") String projectId,
                                       @Param("resCategory") String resCategory);
}