package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.ConsultOrder;
import com.huacainfo.ace.jxb.vo.ConsultOrderQVo;
import com.huacainfo.ace.jxb.vo.ConsultOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsultOrderDao {

    ConsultOrder selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ConsultOrder record);

    int insertSelective(ConsultOrder record);

    int updateByPrimaryKey(ConsultOrder record);

    int updateByPrimaryKeySelective(ConsultOrder record);

    ConsultOrderVo selectVoByPrimaryKey(String id);

    List
            <ConsultOrderVo> findList(@Param("condition") ConsultOrderQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ConsultOrderQVo condition);

    int isExit(ConsultOrder record);

}