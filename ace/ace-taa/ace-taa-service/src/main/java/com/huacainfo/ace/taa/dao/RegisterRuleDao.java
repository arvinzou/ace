package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.taa.model.RegisterRule;
import com.huacainfo.ace.taa.vo.RegisterRuleQVo;
import com.huacainfo.ace.taa.vo.RegisterRuleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegisterRuleDao {

    RegisterRule selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(RegisterRule record);

    int updateByPrimaryKey(RegisterRule record);

    RegisterRuleVo selectVoByPrimaryKey(String id);

    List<RegisterRuleVo> findList(@Param("condition") RegisterRuleQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") RegisterRuleQVo condition);

    int isExist(RegisterRule record);

    int updateStatus(@Param("id") String id, @Param("status") String status);

    RegisterRuleVo findByCondition(@Param("condition") RegisterRuleQVo condition);
}