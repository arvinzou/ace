package com.huacainfo.ace.fundtown.dao;

import com.huacainfo.ace.fundtown.model.ProcessNodeRes;
import com.huacainfo.ace.fundtown.vo.ProcessNodeResQVo;
import com.huacainfo.ace.fundtown.vo.ProcessNodeResVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProcessNodeResDao {

    ProcessNodeRes selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ProcessNodeRes record);

    int insertSelective(ProcessNodeRes record);

    int updateByPrimaryKey(ProcessNodeRes record);

    int updateByPrimaryKeySelective(ProcessNodeRes record);

    ProcessNodeResVo selectVoByPrimaryKey(String id);

    List<ProcessNodeResVo> findList(@Param("condition") ProcessNodeResQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ProcessNodeResQVo condition);

    int isExit(ProcessNodeRes record);

    List<ProcessNodeRes> findByNodeId(String nodeId);
}