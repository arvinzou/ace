package com.huacainfo.ace.society.dao;

import java.util.List;

import com.huacainfo.ace.society.model.Rpt;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.society.model.Circle;
import com.huacainfo.ace.society.vo.CircleQVo;
import com.huacainfo.ace.society.vo.CircleVo;

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


    int updateStatus(@Param("id") String id,@Param("status") String status,@Param("lastAuditLogId") String lastAuditLogId);

    List<Rpt> getList(@Param("start") int start, @Param("limit") int limit);

}