package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopAppealHelp;
import com.huacainfo.ace.fop.vo.FopAppealHelpQVo;
import com.huacainfo.ace.fop.vo.FopAppealHelpVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopAppealHelpDao {

    FopAppealHelp selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopAppealHelp record);

    int insertSelective(FopAppealHelp record);

    int updateByPrimaryKey(FopAppealHelp record);

    int updateByPrimaryKeySelective(FopAppealHelp record);

    FopAppealHelpVo selectVoByPrimaryKey(String id);

    List<FopAppealHelpVo> findList(@Param("condition") FopAppealHelpQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopAppealHelpQVo condition);

    int isExit(FopAppealHelp record);

}