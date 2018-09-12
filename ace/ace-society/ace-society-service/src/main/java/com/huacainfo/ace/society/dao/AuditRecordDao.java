package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.AuditRecord;
import com.huacainfo.ace.society.vo.AuditRecordQVo;
import com.huacainfo.ace.society.vo.AuditRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuditRecordDao {

    AuditRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(AuditRecord record);

    int insertSelective(AuditRecord record);

    int updateByPrimaryKey(AuditRecord record);

    int updateByPrimaryKeySelective(AuditRecord record);

    AuditRecordVo selectVoByPrimaryKey(String id);

    List<AuditRecordVo> findList(@Param("condition") AuditRecordQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") AuditRecordQVo condition);

    int isExit(AuditRecord record);

    AuditRecord findBisType(@Param("bisType") String bisType,
                            @Param("bisId") String bisId,
                            @Param("userId") String userId);
}