package com.huacainfo.ace.fop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.Pm;
import com.huacainfo.ace.fop.vo.PmQVo;
import com.huacainfo.ace.fop.vo.PmVo;

public interface PmDao {

Pm selectByPrimaryKey(String id);

int deleteByPrimaryKey(String id);

int insert(Pm record);


int updateByPrimaryKey(Pm record);


PmVo selectVoByPrimaryKey(String id);

List
<PmVo> findList(@Param("condition") PmQVo condition,
    @Param("start") int start,
    @Param("limit") int limit,
    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") PmQVo condition);

    int isExit(Pm record);

    int updateStatus(Pm record);

    }