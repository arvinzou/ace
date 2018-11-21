package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopCallRecordDetail;

import java.util.List;

public interface FopCallRecordDetailDao {
    int deleteByPrimaryKey(String id);

    int insert(FopCallRecordDetail record);

    int insertSelective(FopCallRecordDetail record);

    FopCallRecordDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FopCallRecordDetail record);

    int updateByPrimaryKey(FopCallRecordDetail record);

    int deleteByRecordId(String recordId);

    List<FopCallRecordDetail> findByRecordId(String recordId);
}