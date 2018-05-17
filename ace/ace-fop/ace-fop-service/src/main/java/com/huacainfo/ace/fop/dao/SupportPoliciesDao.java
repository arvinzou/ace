package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.SupportPolicies;
import com.huacainfo.ace.fop.vo.SupportPoliciesQVo;
import com.huacainfo.ace.fop.vo.SupportPoliciesVo;

public interface SupportPoliciesDao {

    SupportPolicies selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SupportPolicies record);

    int insertSelective(SupportPolicies record);

    int updateByPrimaryKey(SupportPolicies record);

    int updateByPrimaryKeySelective(SupportPolicies record);

    SupportPoliciesVo selectVoByPrimaryKey(String id);

    List
            <SupportPoliciesVo> findList(@Param("condition") SupportPoliciesQVo condition,
                                         @Param("start") int start,
                                         @Param("limit") int limit,
                                         @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SupportPoliciesQVo condition);

    int isExit(SupportPolicies record);

}