package com.huacainfo.ace.fundtown.dao;

import com.huacainfo.ace.fundtown.model.ProcessNode;
import com.huacainfo.ace.fundtown.vo.ProcessNodeQVo;
import com.huacainfo.ace.fundtown.vo.ProcessNodeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProcessNodeDao {

    ProcessNode selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ProcessNode record);

    int insertSelective(ProcessNode record);

    int updateByPrimaryKey(ProcessNode record);

    int updateByPrimaryKeySelective(ProcessNode record);

    ProcessNodeVo selectVoByPrimaryKey(String id);

    List<ProcessNodeVo> findList(@Param("condition") ProcessNodeQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ProcessNodeQVo condition);

    int isExit(ProcessNode record);

    List<ProcessNode> findNodeList();

    ProcessNode findBySequence(String sequence);
}