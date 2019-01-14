package com.huacainfo.ace.partyschool.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.partyschool.vo.ClassesVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.NoticeStatus;
import com.huacainfo.ace.partyschool.vo.NoticeStatusQVo;
import com.huacainfo.ace.partyschool.vo.NoticeStatusVo;

public interface NoticeStatusDao {

    NoticeStatus selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(NoticeStatus record);


    int updateByPrimaryKey(NoticeStatus record);


    NoticeStatusVo selectVoByPrimaryKey(String id);

    List<NoticeStatusVo> findList(@Param("condition") NoticeStatusQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    List<NoticeStatusVo> findMyNoticeList(String userId);


    int updateStatus(@Param("id") String id);

 }