package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.RoadDangerReportPic;

import java.util.List;

public interface RoadDangerReportPicDao {
    int deleteByPrimaryKey(String id);

    int insert(RoadDangerReportPic record);

    int insertSelective(RoadDangerReportPic record);

    RoadDangerReportPic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoadDangerReportPic record);

    int updateByPrimaryKey(RoadDangerReportPic record);

    List<RoadDangerReportPic> selectNochangedMethodImg(String id);

    List<RoadDangerReportPic> selectChangedMethodImg(String id);
}