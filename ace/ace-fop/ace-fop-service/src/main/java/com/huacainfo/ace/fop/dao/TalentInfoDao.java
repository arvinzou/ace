package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.TalentInfo;
import com.huacainfo.ace.fop.vo.TalentInfoQVo;
import com.huacainfo.ace.fop.vo.TalentInfoVo;

public interface TalentInfoDao {

    TalentInfo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TalentInfo record);

    int insertSelective(TalentInfo record);

    int updateByPrimaryKey(TalentInfo record);

    int updateByPrimaryKeySelective(TalentInfo record);

    TalentInfoVo selectVoByPrimaryKey(String id);

    List
            <TalentInfoVo> findList(@Param("condition") TalentInfoQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TalentInfoQVo condition);

    int isExit(TalentInfo record);

}