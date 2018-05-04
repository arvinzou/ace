package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopMember;
import com.huacainfo.ace.fop.vo.FopMemberQVo;
import com.huacainfo.ace.fop.vo.FopMemberVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopMemberDao {

    FopMember selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopMember record);

    int insertSelective(FopMember record);

    int updateByPrimaryKey(FopMember record);

    int updateByPrimaryKeySelective(FopMember record);

    FopMemberVo selectVoByPrimaryKey(String id);

    List<FopMemberVo> findList(@Param("condition") FopMemberQVo condition,
                               @Param("start") int start, @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopMemberQVo condition);

    int isExit(FopMember record);

}