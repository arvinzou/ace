package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuProjectUseRecord;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordQVo;
import com.huacainfo.ace.cu.vo.CuProjectUseRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CuProjectUseRecordDao {

    CuProjectUseRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuProjectUseRecord record);

    int insertSelective(CuProjectUseRecord record);

    int updateByPrimaryKey(CuProjectUseRecord record);

    int updateByPrimaryKeySelective(CuProjectUseRecord record);

    CuProjectUseRecordVo selectVoByPrimaryKey(String id);

    List<CuProjectUseRecordVo> findList(@Param("condition") CuProjectUseRecordQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuProjectUseRecordQVo condition);

    int isExit(CuProjectUseRecord record);

}