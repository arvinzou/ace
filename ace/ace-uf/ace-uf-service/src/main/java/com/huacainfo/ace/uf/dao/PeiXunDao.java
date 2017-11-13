package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.PeiXun;
import com.huacainfo.ace.uf.model.XinXi;
import com.huacainfo.ace.uf.vo.PeiXunQVo;
import com.huacainfo.ace.uf.vo.PeiXunVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeiXunDao {
    int deleteByPrimaryKey(String peixunId);

    int insert(PeiXun record);


    PeiXun selectPeiXunByPrimaryKey(String peixunId);


    int updateByPrimaryKey(PeiXun record);

    List<PeiXunVo> findList(@Param("condition") PeiXunQVo condition,
                            @Param("start") int start, @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") PeiXunQVo condition);

    int isExit(PeiXun record);
}