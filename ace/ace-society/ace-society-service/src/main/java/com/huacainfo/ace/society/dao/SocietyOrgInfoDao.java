package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.SocietyOrgInfo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoQVo;
import com.huacainfo.ace.society.vo.SocietyOrgInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SocietyOrgInfoDao {

    SocietyOrgInfo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SocietyOrgInfo record);

    int insertSelective(SocietyOrgInfo record);

    int updateByPrimaryKey(SocietyOrgInfo record);

    int updateByPrimaryKeySelective(SocietyOrgInfo record);

    SocietyOrgInfoVo selectVoByPrimaryKey(String id);

    List
            <SocietyOrgInfoVo> findList(@Param("condition") SocietyOrgInfoQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SocietyOrgInfoQVo condition);

    int isExit(SocietyOrgInfo record);

}