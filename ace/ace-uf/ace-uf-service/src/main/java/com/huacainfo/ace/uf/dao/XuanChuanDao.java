package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.XuanChuan;
import com.huacainfo.ace.uf.vo.XuanChuanQVo;
import com.huacainfo.ace.uf.vo.XuanChuanVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XuanChuanDao {
    int deleteByPrimaryKey(String XuanChuanId);

    int insert(XuanChuan record);


    XuanChuanVo selectByPrimaryKey(String XuanChuanId);


    int updateByPrimaryKey(XuanChuan record);

    List<XuanChuanVo> findList(@Param("condition") XuanChuanQVo condition,
                             @Param("start") int start, @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

	int findCount(@Param("condition") XuanChuanQVo condition);

	int isExit(XuanChuan record);

}