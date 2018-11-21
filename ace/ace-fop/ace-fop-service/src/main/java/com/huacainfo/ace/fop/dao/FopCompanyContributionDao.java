package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopCompanyContribution;
import com.huacainfo.ace.fop.vo.FopCompanyContributionQVo;
import com.huacainfo.ace.fop.vo.FopCompanyContributionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopCompanyContributionDao {

    int deleteByPrimaryKey(String id);

    int deleteByCID(String cid);

    int insert(FopCompanyContribution record);

    int insertSelective(FopCompanyContribution record);

    FopCompanyContribution selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FopCompanyContribution record);

    int updateByPrimaryKeyWithBLOBs(FopCompanyContribution record);

    int updateByPrimaryKey(FopCompanyContribution record);

    FopCompanyContributionVo selectVoByPrimaryKey(String id);

    List<FopCompanyContributionVo> findList(@Param("condition") FopCompanyContributionQVo condition,
                                            @Param("start") int start, @Param("limit") int limit,
                                            @Param("orderBy") String orderBy);

    List<FopCompanyContributionVo> findListCID(@Param("companyId") String companyId);

    int findCount(@Param("condition") FopCompanyContributionQVo condition);

    int isExit(FopCompanyContribution record);

}