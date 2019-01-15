package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.Notice;
import com.huacainfo.ace.partyschool.vo.NoticeQVo;
import com.huacainfo.ace.partyschool.vo.NoticeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeDao {

    Notice selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Notice record);


    int updateByPrimaryKey(Notice record);


    NoticeVo selectVoByPrimaryKey(String id);

    List<NoticeVo> findList(@Param("condition") NoticeQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    List<NoticeVo> findMyNoticeList(String id);

    int findCount(@Param("condition") NoticeQVo condition);

    int findUnreadCount(String id);

    int isExit(Notice record);

    int updateStatus(Notice record);

}