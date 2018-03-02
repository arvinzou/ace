package com.huacainfo.ace.live.dao;

import java.util.List;

import com.huacainfo.ace.portal.model.SensitiveWords;
import com.huacainfo.ace.portal.vo.SensitiveWordsVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.live.model.LiveCmt;
import com.huacainfo.ace.live.vo.LiveCmtQVo;
import com.huacainfo.ace.live.vo.LiveCmtVo;

public interface LiveCmtDao {
    int deleteByPrimaryKey(String LiveCmtId);

    int insert(LiveCmt record);


    LiveCmtVo selectByPrimaryKey(String LiveCmtId);


    int updateByPrimaryKey(@Param("id") String id, @Param("status") String status);

    List<LiveCmtVo> findList(@Param("condition") LiveCmtQVo condition,
                             @Param("start") int start, @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LiveCmtQVo condition);

    int isExit(LiveCmt record);

    List<SensitiveWordsVo> findSensitiveWordsList(SensitiveWords record);
}