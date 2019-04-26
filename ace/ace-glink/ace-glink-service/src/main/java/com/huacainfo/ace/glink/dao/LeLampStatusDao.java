package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.LeLampStatus;
import com.huacainfo.ace.glink.vo.LeLampStatusQVo;
import com.huacainfo.ace.glink.vo.LeLampStatusVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeLampStatusDao {

    LeLampStatus selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(LeLampStatus record);

    int updateByPrimaryKey(LeLampStatus record);

    LeLampStatusVo selectVoByPrimaryKey(String id);

    List<LeLampStatusVo> findList(@Param("condition") LeLampStatusQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LeLampStatusQVo condition);

    int isExit(LeLampStatus record);

    int deleteByCheckDate(String checkDate);
}
