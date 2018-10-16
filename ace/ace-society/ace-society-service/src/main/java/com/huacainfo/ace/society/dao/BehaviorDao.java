package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.Behavior;
import com.huacainfo.ace.society.vo.BehaviorQVo;
import com.huacainfo.ace.society.vo.BehaviorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BehaviorDao {

    Behavior selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Behavior record);

    int insertSelective(Behavior record);

    int updateByPrimaryKey(Behavior record);

    int updateByPrimaryKeySelective(Behavior record);

    BehaviorVo selectVoByPrimaryKey(String id);

    List<BehaviorVo> findList(@Param("condition") BehaviorQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BehaviorQVo condition);

    int isExit(Behavior record);

    int updateStatus(Behavior record);
}