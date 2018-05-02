package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopCompanyActivity;
import com.huacainfo.ace.fop.model.FopCompanyOrg;
import com.huacainfo.ace.fop.vo.FopCompanyOrgQVo;
import com.huacainfo.ace.fop.vo.FopCompanyOrgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopCompanyOrgDao {

    int deleteByPrimaryKey(String id);

    int insert(FopCompanyOrg record);

    int insertSelective(FopCompanyOrg record);

    FopCompanyOrg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FopCompanyOrg record);

    int updateByPrimaryKey(FopCompanyOrg record);

    FopCompanyOrgVo selectVoByPrimaryKey(String id);

    List<FopCompanyOrgVo> findList(@Param("condition") FopCompanyOrgQVo condition,
                                   @Param("start") int start, @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopCompanyOrgQVo condition);

    int isExit(FopCompanyActivity record);

}