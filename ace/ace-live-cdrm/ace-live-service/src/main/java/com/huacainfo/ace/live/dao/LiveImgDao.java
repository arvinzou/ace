package com.huacainfo.ace.live.dao;

import java.util.List;

import com.huacainfo.ace.live.vo.LiveQVo;
import com.huacainfo.ace.live.vo.LiveVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.live.model.LiveImg;
import com.huacainfo.ace.live.vo.LiveImgQVo;
import com.huacainfo.ace.live.vo.LiveImgVo;

public interface LiveImgDao {
    int deleteByPrimaryKey(String LiveImgId);

    int deleteByRptId(String rptId);

    int insert(LiveImg record);


    LiveImgVo selectByPrimaryKey(String LiveImgId);


    int updateByPrimaryKey(LiveImg record);

    List<LiveImgVo> findList(@Param("condition") LiveImgQVo condition, @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LiveImgQVo condition);

    int isExit(LiveImg record);

    int deleteByRid(String rptId);
}