package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.PointsRecord;
import com.huacainfo.ace.society.vo.PointsRecordQVo;
import com.huacainfo.ace.society.vo.PointsRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PointsRecordDao {

    PointsRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(PointsRecord record);


    int updateByPrimaryKey(PointsRecord record);


    PointsRecordVo selectVoByPrimaryKey(String id);

    List
            <PointsRecordVo> findList(@Param("condition") PointsRecordQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") PointsRecordQVo condition);

    int isExit(PointsRecord record);

    int updateStatus(PointsRecord record);

}