package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.CounselorCheckResult;

public interface CounselorCheckResultDao {
    int deleteByPrimaryKey(String id);

    int insert(CounselorCheckResult record);

    int insertSelective(CounselorCheckResult record);

    CounselorCheckResult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CounselorCheckResult record);

    int updateByPrimaryKey(CounselorCheckResult record);
}