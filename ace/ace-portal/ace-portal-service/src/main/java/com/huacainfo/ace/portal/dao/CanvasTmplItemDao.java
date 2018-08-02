package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.CanvasTmplItem;
import com.huacainfo.ace.portal.vo.CanvasTmplItemQVo;
import com.huacainfo.ace.portal.vo.CanvasTmplItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CanvasTmplItemDao {
    int deleteByPrimaryKey(String CanvasTmplItemId);

    int insert(CanvasTmplItem record);


    CanvasTmplItemVo selectByPrimaryKey(String CanvasTmplItemId);

    int updateByPrimaryKey(CanvasTmplItem record);

    List<CanvasTmplItemVo> findList(@Param("condition") CanvasTmplItemQVo condition,
                                    @Param("start") int start, @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CanvasTmplItemQVo condition);

    int isExit(CanvasTmplItem record);

    List<Map<String, Object>> getList(Map<String, Object> params);

    Map<String, Object> getById(String id);

    List<CanvasTmplItem> findItemList(String tmplId);
}