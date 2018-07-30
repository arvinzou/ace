package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.StudioImg;
import com.huacainfo.ace.jxb.vo.StudioImgQVo;
import com.huacainfo.ace.jxb.vo.StudioImgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudioImgDao {

    StudioImg selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(StudioImg record);

    int insertSelective(StudioImg record);

    int updateByPrimaryKey(StudioImg record);

    int updateByPrimaryKeySelective(StudioImg record);

    StudioImgVo selectVoByPrimaryKey(String id);

    List
            <StudioImgVo> findList(@Param("condition") StudioImgQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") StudioImgQVo condition);

    int isExit(StudioImg record);

    List<StudioImg> finImgList(String id);
}