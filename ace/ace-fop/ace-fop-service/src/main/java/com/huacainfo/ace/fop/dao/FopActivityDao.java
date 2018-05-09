package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopActivity;
import com.huacainfo.ace.fop.vo.FopActivityQVo;
import com.huacainfo.ace.fop.vo.FopActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopActivityDao {

    FopActivity selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopActivity record);

    int insertSelective(FopActivity record);

    int updateByPrimaryKey(FopActivity record);

    int updateByPrimaryKeySelective(FopActivity record);

    FopActivityVo selectVoByPrimaryKey(String id);

    List<FopActivityVo> findList(@Param("condition") FopActivityQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopActivityQVo condition);

    int isExit(FopActivity record);

}