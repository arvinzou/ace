package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.Circle;
import com.huacainfo.ace.society.model.Rpt;
import com.huacainfo.ace.society.vo.CircleQVo;
import com.huacainfo.ace.society.vo.CircleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CircleDao {


    int deleteByPrimaryKey(String id);

    int insert(Circle record);


    int updateByPrimaryKey(Circle record);


    CircleVo selectVoByPrimaryKey(String id);

    List<CircleVo> findList(@Param("condition") CircleQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CircleQVo condition);


    int updateStatus(@Param("id") String id,
                     @Param("status") String status,
                     @Param("lastAuditLogId") String lastAuditLogId);

    List<Rpt> getList(@Param("status") String status,
                      @Param("uid") String uid,
                      @Param("start") int start,
                      @Param("limit") int limit);

    int updateAddLike(@Param("id") String id);

}