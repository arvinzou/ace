package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopFlowRecord;
import com.huacainfo.ace.fop.vo.FopFlowRecordQVo;
import com.huacainfo.ace.fop.vo.FopFlowRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopFlowRecordDao {

    FopFlowRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopFlowRecord record);

    int insertSelective(FopFlowRecord record);

    int updateByPrimaryKey(FopFlowRecord record);

    int updateByPrimaryKeySelective(FopFlowRecord record);

    FopFlowRecordVo selectVoByPrimaryKey(String id);

    List<FopFlowRecordVo> findList(@Param("condition") FopFlowRecordQVo condition,
                                   @Param("start") int start, @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopFlowRecordQVo condition);

    int isExit(FopFlowRecord record);

    FopFlowRecord findByFromId(@Param("fromId") String fromId,
                               @Param("flowType") String flowType);
}