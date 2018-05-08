package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopGeHelp;
import com.huacainfo.ace.fop.vo.FopGeHelpQVo;
import com.huacainfo.ace.fop.vo.FopGeHelpVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopGeHelpDao {

    FopGeHelp selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopGeHelp record);

    int insertSelective(FopGeHelp record);

    int updateByPrimaryKey(FopGeHelp record);

    int updateByPrimaryKeySelective(FopGeHelp record);

    FopGeHelpVo selectVoByPrimaryKey(String id);

    List<FopGeHelpVo> findList(@Param("condition") FopGeHelpQVo condition,
                               @Param("start") int start, @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopGeHelpQVo condition);

    int isExit(FopGeHelp record);

}