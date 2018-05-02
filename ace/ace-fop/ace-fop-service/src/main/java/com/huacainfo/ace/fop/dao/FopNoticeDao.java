package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopNotice;
import com.huacainfo.ace.fop.vo.FopNoticeQVo;
import com.huacainfo.ace.fop.vo.FopNoticeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopNoticeDao {

    FopNotice selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopNotice record);

    int insertSelective(FopNotice record);

    int updateByPrimaryKey(FopNotice record);

    int updateByPrimaryKeySelective(FopNotice record);

    FopNoticeVo selectVoByPrimaryKey(String id);

    List<FopNoticeVo> findList(@Param("condition") FopNoticeQVo condition,
                               @Param("start") int start, @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopNoticeQVo condition);

    int isExit(FopNotice record);

}