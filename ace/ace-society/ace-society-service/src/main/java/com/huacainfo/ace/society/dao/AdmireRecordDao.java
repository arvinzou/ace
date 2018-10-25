package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.AdmireRecord;
import com.huacainfo.ace.society.vo.AdmireRecordQVo;
import com.huacainfo.ace.society.vo.AdmireRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdmireRecordDao {

    AdmireRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(AdmireRecord record);


    int updateByPrimaryKey(AdmireRecord record);


    AdmireRecordVo selectVoByPrimaryKey(String id);

    List<AdmireRecordVo> findList(@Param("condition") AdmireRecordQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") AdmireRecordQVo condition);

    int isExit(AdmireRecord record);

    int updateStatus(AdmireRecord record);

    int cancelAdmire(@Param("bisType") String bisType,
                     @Param("bisId") String bisId,
                     @Param("userId") String userId);

    int getAdmireNum(String bisType, String bisId);
}