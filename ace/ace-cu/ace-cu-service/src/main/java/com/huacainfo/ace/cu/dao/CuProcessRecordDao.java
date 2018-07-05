package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuProcessRecord;
import com.huacainfo.ace.cu.vo.CuProcessRecordQVo;
import com.huacainfo.ace.cu.vo.CuProcessRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CuProcessRecordDao {

    CuProcessRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuProcessRecord record);

    int insertSelective(CuProcessRecord record);

    int updateByPrimaryKey(CuProcessRecord record);

    int updateByPrimaryKeySelective(CuProcessRecord record);

    CuProcessRecordVo selectVoByPrimaryKey(String id);

    List
            <CuProcessRecordVo> findList(@Param("condition") CuProcessRecordQVo condition,
                                         @Param("start") int start,
                                         @Param("limit") int limit,
                                         @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuProcessRecordQVo condition);

    int isExit(CuProcessRecord record);

}