package com.huacainfo.ace.fundtown.dao;

import com.huacainfo.ace.fundtown.model.VipMemberRes;
import com.huacainfo.ace.fundtown.vo.VipMemberResQVo;
import com.huacainfo.ace.fundtown.vo.VipMemberResVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VipMemberResDao {

    VipMemberRes selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(VipMemberRes record);

    int insertSelective(VipMemberRes record);

    int updateByPrimaryKey(VipMemberRes record);

    int updateByPrimaryKeySelective(VipMemberRes record);

    VipMemberResVo selectVoByPrimaryKey(String id);

    List<VipMemberResVo> findList(@Param("condition") VipMemberResQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") VipMemberResQVo condition);

    int isExit(VipMemberRes record);

}