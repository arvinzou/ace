package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopFinanceProject;
import com.huacainfo.ace.fop.vo.FopFinanceProjectQVo;
import com.huacainfo.ace.fop.vo.FopFinanceProjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopFinanceProjectDao {

    FopFinanceProject selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopFinanceProject record);

    int insertSelective(FopFinanceProject record);

    int updateByPrimaryKey(FopFinanceProject record);

    int updateByPrimaryKeySelective(FopFinanceProject record);


    FopFinanceProjectVo selectVoByPrimaryKey(String id);

    List<FopFinanceProjectVo> findList(@Param("condition") FopFinanceProjectQVo condition,
                                       @Param("start") int start, @Param("limit") int limit,
                                       @Param("orderBy") String orderBy);

    List<FopFinanceProjectVo> findListVo(@Param("condition") FopFinanceProjectQVo condition,
                                         @Param("start") int start, @Param("limit") int limit,
                                         @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopFinanceProjectQVo condition);

    int isExit(FopFinanceProject record);
}