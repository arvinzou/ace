package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.CorporateStyle;
import com.huacainfo.ace.fop.vo.CorporateStyleQVo;
import com.huacainfo.ace.fop.vo.CorporateStyleVo;

public interface CorporateStyleDao {

    CorporateStyle selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CorporateStyle record);

    int insertSelective(CorporateStyle record);

    int updateByPrimaryKey(CorporateStyle record);

    int updateByPrimaryKeySelective(CorporateStyle record);

    CorporateStyleVo selectVoByPrimaryKey(String id);

    List
            <CorporateStyleVo> findList(@Param("condition") CorporateStyleQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CorporateStyleQVo condition);

    int isExit(CorporateStyle record);

}