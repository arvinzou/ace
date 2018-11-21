package com.huacainfo.ace.society.dao;
import com.huacainfo.ace.society.model.CircleCmt;
import com.huacainfo.ace.society.vo.CircleCmtQVo;
import com.huacainfo.ace.society.vo.CircleCmtVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CircleCmtDao {


    int deleteByPrimaryKey(String id);


    int insert(CircleCmt record);

    CircleCmtVo selectVoByPrimaryKey(String id);

    List<CircleCmtVo> findList(@Param("condition") CircleCmtQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CircleCmtQVo condition);


    int updateStatus(@Param("id") String id,@Param("status") String status);
}