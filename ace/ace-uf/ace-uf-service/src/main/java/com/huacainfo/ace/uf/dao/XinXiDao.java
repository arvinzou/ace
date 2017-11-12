package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.XinXi;
import com.huacainfo.ace.uf.vo.XinXiQVo;
import com.huacainfo.ace.uf.vo.XinXiVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XinXiDao {
    int deleteByPrimaryKey(String XinXiId);

    int insert(XinXi record);


    XinXiVo selectByPrimaryKey(String XinXiId);


    int updateByPrimaryKey(XinXi record);

    List<XinXiVo> findList(@Param("condition") XinXiQVo condition,
                             @Param("start") int start, @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

	int findCount(@Param("condition") XinXiQVo condition);

	int isExit(XinXi record);

}