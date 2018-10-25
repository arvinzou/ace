package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.CommentRecord;
import com.huacainfo.ace.society.vo.CommentRecordQVo;
import com.huacainfo.ace.society.vo.CommentRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentRecordDao {

    CommentRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CommentRecord record);


    int updateByPrimaryKey(CommentRecord record);


    CommentRecordVo selectVoByPrimaryKey(String id);

    List<CommentRecordVo> findList(@Param("condition") CommentRecordQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CommentRecordQVo condition);

    int isExit(CommentRecord record);

    int updateStatus(CommentRecord record);

}