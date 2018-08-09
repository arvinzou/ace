package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.CounselorCheckResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CounselorCheckResultDao {
    int deleteByPrimaryKey(String id);

    int insert(CounselorCheckResult record);

    int insertSelective(CounselorCheckResult record);

    CounselorCheckResult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CounselorCheckResult record);

    int updateByPrimaryKey(CounselorCheckResult record);

    int cleanData(@Param("year") String year,
                  @Param("quarter") String quarter);

    List<CounselorCheckResult> findByQuarter(@Param("year") String year,
                                             @Param("quarter") String quarter);

    CounselorCheckResult findByCounselorId(@Param("year") String year,
                                           @Param("quarter") String quarter,
                                           @Param("counselorId") String counselorId);
}