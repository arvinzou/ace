package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.model.Notice;
import com.huacainfo.ace.policeschool.vo.NoticeQVo;
import com.huacainfo.ace.policeschool.vo.NoticeStatusQVo;
import com.huacainfo.ace.policeschool.vo.NoticeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<NoticeVo> findMyNoticeList(@Param("condition") NoticeStatusQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);


    List<NoticeVo> findPublicNoticeList(@Param("classesId") String classesId,@Param("server") String server);


    int findCount(@Param("condition") NoticeQVo condition);
    int findCountVo(@Param("condition") NoticeQVo condition);

    List<NoticeVo> findListVo(@Param("condition") NoticeQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findUnreadCount(String id);

    int isExit(Notice record);

    int updateStatus(@Param("noticeId") String noticeId, @Param("userId") String userId);

    List<Map<String, Object>> selectFilesById(@Param("id") String id, @Param("server") String server);


    List<Map<String, Object>> selectUsersById(@Param("id") String id);

}