package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopProject;
import com.huacainfo.ace.fop.vo.FopProjectQVo;
import com.huacainfo.ace.fop.vo.FopProjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopProjectDao {

    FopProject selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopProject record);

    int insertSelective(FopProject record);

    int updateByPrimaryKey(FopProject record);

    int updateByPrimaryKeySelective(FopProject record);

    FopProjectVo selectVoByPrimaryKey(String id);

    List<FopProjectVo> findList(@Param("condition") FopProjectQVo condition,
                                @Param("start") int start, @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopProjectQVo condition);

    int isExit(FopProject record);

}