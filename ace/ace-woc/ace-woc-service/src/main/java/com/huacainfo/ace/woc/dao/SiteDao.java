package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Site;
import com.huacainfo.ace.woc.model.Vehicle;
import com.huacainfo.ace.woc.vo.SiteQVo;
import com.huacainfo.ace.woc.vo.SiteVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SiteDao {
    int deleteByPrimaryKey(String id);

    int insert(Site record);

    int insertSelective(Site record);

    SiteVo selectByPrimaryKey(String id);

    SiteVo selectByPrimaryKeyInfo(String id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);

    int updateByPrimaryKey(Vehicle record);

    List<SiteVo> findList(@Param("condition") SiteQVo condition,
                          @Param("start") int start, @Param("limit") int limit,
                          @Param("orderBy") String orderBy);

    List<SiteVo> findListInfo(@Param("condition") SiteQVo condition,
                              @Param("start") int start, @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SiteQVo condition);

    int isExit(Site record);
    List<Map<String, String>> selectSite(
            @Param("params") Map<String, Object> params);

    List<Site> selectAll();
}