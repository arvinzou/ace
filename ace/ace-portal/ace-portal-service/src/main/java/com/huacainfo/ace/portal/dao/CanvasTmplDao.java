package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.CanvasTmpl;
import com.huacainfo.ace.portal.vo.CanvasTmplQVo;
import com.huacainfo.ace.portal.vo.CanvasTmplVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CanvasTmplDao {
    int deleteByPrimaryKey(String CanvasTmplId);

    int insert(CanvasTmpl record);


    CanvasTmplVo selectByPrimaryKey(String CanvasTmplId);


    int updateByPrimaryKey(CanvasTmpl record);

    List<CanvasTmplVo> findList(@Param("condition") CanvasTmplQVo condition,
                                @Param("start") int start, @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CanvasTmplQVo condition);

    int isExit(CanvasTmpl record);

    List<Map<String, Object>> getList(Map<String, Object> params);

    Map<String, Object> getById(String id);

}