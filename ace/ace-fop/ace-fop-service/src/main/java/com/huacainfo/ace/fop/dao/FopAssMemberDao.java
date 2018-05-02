package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopAssMember;
import com.huacainfo.ace.fop.vo.FopAssMemberQVo;
import com.huacainfo.ace.fop.vo.FopAssMemberVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopAssMemberDao {
    int deleteByPrimaryKey(String id);

    int insert(FopAssMember record);

    int insertSelective(FopAssMember record);

    FopAssMember selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FopAssMember record);

    int updateByPrimaryKey(FopAssMember record);

    FopAssMemberVo selectVoByPrimaryKey(String id);

    List<FopAssMemberVo> findList(@Param("condition") FopAssMemberQVo condition,
                                  @Param("start") int start, @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopAssMemberQVo condition);

    int isExit(FopAssMember record);
}