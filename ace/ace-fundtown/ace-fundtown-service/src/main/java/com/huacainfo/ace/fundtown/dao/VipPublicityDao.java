package com.huacainfo.ace.fundtown.dao;

import com.huacainfo.ace.fundtown.model.VipPublicity;
import com.huacainfo.ace.fundtown.vo.VipPublicityQVo;
import com.huacainfo.ace.fundtown.vo.VipPublicityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VipPublicityDao {

    VipPublicity selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(VipPublicity record);

    int insertSelective(VipPublicity record);

    int updateByPrimaryKey(VipPublicity record);

    int updateByPrimaryKeySelective(VipPublicity record);

    VipPublicityVo selectVoByPrimaryKey(String id);

    List<VipPublicityVo> findList(@Param("condition") VipPublicityQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") VipPublicityQVo condition);

    int isExit(VipPublicity record);

}