package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopCompanyActivity;
import com.huacainfo.ace.fop.vo.FopCompanyActivityQVo;
import com.huacainfo.ace.fop.vo.FopCompanyActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopCompanyActivityDao {
    int deleteByPrimaryKey(String id);

    int insert(FopCompanyActivity record);

    int insertSelective(FopCompanyActivity record);

    FopCompanyActivity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FopCompanyActivity record);

    int updateByPrimaryKey(FopCompanyActivity record);

    FopCompanyActivityVo selectVoByPrimaryKey(String id);

    List<FopCompanyActivityVo> findList(@Param("condition") FopCompanyActivityQVo condition,
                                        @Param("start") int start, @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopCompanyActivityQVo condition);

    int isExit(FopCompanyActivity record);

}