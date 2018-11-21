package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.FopVisit;
import com.huacainfo.ace.fop.vo.FopVisitQVo;
import com.huacainfo.ace.fop.vo.FopVisitVo;

public interface FopVisitDao {

    FopVisit selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopVisit record);


    int updateByPrimaryKey(FopVisit record);


    FopVisitVo selectVoByPrimaryKey(String id);

    List<FopVisitVo> findList(@Param("condition") FopVisitQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopVisitQVo condition);

    int isExit(FopVisit record);

    int updateStatus(FopVisit record);

}