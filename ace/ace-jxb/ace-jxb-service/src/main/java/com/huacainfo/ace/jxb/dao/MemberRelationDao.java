package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.MemberRelation;
import com.huacainfo.ace.jxb.vo.MemberRelationQVo;
import com.huacainfo.ace.jxb.vo.MemberRelationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberRelationDao {

    MemberRelation selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(MemberRelation record);

    int insertSelective(MemberRelation record);

    int updateByPrimaryKey(MemberRelation record);

    int updateByPrimaryKeySelective(MemberRelation record);

    MemberRelationVo selectVoByPrimaryKey(String id);

    List<MemberRelationVo> findList(@Param("condition") MemberRelationQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") MemberRelationQVo condition);

    int isExit(MemberRelation record);

    MemberRelation findByOpenid(String openid);
}