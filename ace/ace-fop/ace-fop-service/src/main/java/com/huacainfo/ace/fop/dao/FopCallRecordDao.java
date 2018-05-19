package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopCallRecord;
import com.huacainfo.ace.fop.vo.FopCallRecordQVo;
import com.huacainfo.ace.fop.vo.FopCallRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FopCallRecordDao {

    FopCallRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopCallRecord record);

    int insertSelective(FopCallRecord record);

    int updateByPrimaryKey(FopCallRecord record);

    int updateByPrimaryKeySelective(FopCallRecord record);

    FopCallRecordVo selectVoByPrimaryKey(String id);

    List<FopCallRecordVo> findList(@Param("condition") FopCallRecordQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopCallRecordQVo condition);

    int isExit(FopCallRecord record);

    List<Map<String, Object>> selectAllSendObject();
}