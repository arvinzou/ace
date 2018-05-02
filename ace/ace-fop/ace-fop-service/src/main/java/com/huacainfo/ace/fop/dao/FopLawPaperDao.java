package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopLawPaper;
import com.huacainfo.ace.fop.vo.FopLawPaperQVo;
import com.huacainfo.ace.fop.vo.FopLawPaperVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopLawPaperDao {

    FopLawPaper selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopLawPaper record);

    int insertSelective(FopLawPaper record);

    int updateByPrimaryKey(FopLawPaper record);

    int updateByPrimaryKeySelective(FopLawPaper record);

    FopLawPaperVo selectVoByPrimaryKey(String id);

    List<FopLawPaperVo> findList(@Param("condition") FopLawPaperQVo condition,
                                 @Param("start") int start, @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopLawPaperQVo condition);

    int isExit(FopLawPaper record);

}