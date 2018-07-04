package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopPerson;
import com.huacainfo.ace.fop.vo.FopPersonQVo;
import com.huacainfo.ace.fop.vo.FopPersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopPersonDao {
    int deleteByPrimaryKey(String id);

    int insert(FopPerson record);

    int insertSelective(FopPerson record);

    FopPerson selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FopPerson record);

    int updateByPrimaryKeyWithBLOBs(FopPerson record);

    int updateByPrimaryKey(FopPerson record);

    FopPersonVo selectVoByPrimaryKey(String id);

    List<FopPersonVo> findList(@Param("condition") FopPersonQVo condition,
                               @Param("start") int start, @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopPersonQVo condition);

    int isExit(FopPerson record);

    FopPerson selectByMobile(String mobileNumber);
}