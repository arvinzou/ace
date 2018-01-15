package com.huacainfo.ace.live.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.vo.LiveImgQVo;
import com.huacainfo.ace.live.vo.LiveImgVo;

public interface LiveImgDao {
    int deleteByPrimaryKey(String LiveImgId);

    int insert(LiveImg record);


    LiveImgVo selectByPrimaryKey(String LiveImgId);


    int updateByPrimaryKey(LiveImg record);

    List<LiveImgVo> findList(@Param("condition") LiveImgQVo condition,
                             @Param("start") int start, @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LiveImgQVo condition);

    int isExit(LiveImg record);

}