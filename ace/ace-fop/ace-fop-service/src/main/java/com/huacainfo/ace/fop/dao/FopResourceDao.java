package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopResource;
import com.huacainfo.ace.fop.vo.FopResourceQVo;
import com.huacainfo.ace.fop.vo.FopResourceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopResourceDao {
    int deleteByPrimaryKey(String id);

    int insert(FopResource record);

    int insertSelective(FopResource record);

    FopResource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FopResource record);

    int updateByPrimaryKey(FopResource record);

    FopResourceVo selectVoByPrimaryKey(String id);

    List<FopResourceVo> findList(@Param("condition") FopResourceQVo condition,
                                 @Param("start") int start, @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopResourceQVo condition);

    int isExit(FopResource record);

}