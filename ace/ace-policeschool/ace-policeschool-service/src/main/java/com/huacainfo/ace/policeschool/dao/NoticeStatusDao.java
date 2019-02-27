package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.NoticeStatus;
import com.huacainfo.ace.policeschool.vo.NoticeStatusQVo;
import com.huacainfo.ace.policeschool.vo.NoticeStatusVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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


    public List<Map<String, Object>> getPushUsersList(String id);

    int findCount(@Param("userId") String userId,
                  @Param("noticeId") String noticeId);

}