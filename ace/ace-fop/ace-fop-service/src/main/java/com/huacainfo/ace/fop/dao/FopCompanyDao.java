package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.vo.FopCompanyQVo;
import com.huacainfo.ace.fop.vo.FopCompanyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopCompanyDao {

    int deleteByPrimaryKey(String id);

    int insert(FopCompany record);

    int insertSelective(FopCompany record);

    FopCompany selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FopCompany record);

    int updateByPrimaryKey(FopCompany record);

    FopCompanyVo selectVoByPrimaryKey(String id);

    List<FopCompanyVo> findList(@Param("condition") FopCompanyQVo condition,
                                @Param("start") int start, @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopCompanyQVo condition);

    int isExit(FopCompany record);

}