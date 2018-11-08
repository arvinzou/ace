package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopCompanyAnnex;

import java.util.List;

public interface FopCompanyAnnexDao {
    int deleteByPrimaryKey(String id);

    int insert(FopCompanyAnnex record);

    FopCompanyAnnex selectByPrimaryKey(String id);

    int updateByPrimaryKey(FopCompanyAnnex record);

    List<FopCompanyAnnex> findByComId(String comId);
}