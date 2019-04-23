package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.PagePortal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PagePortalDao {
    int deleteByPrimaryKey(String id);

    int insert(PagePortal record);

    PagePortal selectByPrimaryKey(String id);

    int updateByPrimaryKey(PagePortal record);

    PagePortal findByKey(@Param("itemKey") String itemKey,
                         @Param("sysId") String sysId);

    List<PagePortal> findListBySysId(String sysId);
}
