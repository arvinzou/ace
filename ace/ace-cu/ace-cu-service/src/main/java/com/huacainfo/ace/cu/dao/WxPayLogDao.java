package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.WxPayLog;
import com.huacainfo.ace.cu.vo.WxPayLogQVo;
import com.huacainfo.ace.cu.vo.WxPayLogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxPayLogDao {

    WxPayLog selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(WxPayLog record);

    int insertSelective(WxPayLog record);

    int updateByPrimaryKey(WxPayLog record);

    int updateByPrimaryKeySelective(WxPayLog record);

    WxPayLogVo selectVoByPrimaryKey(String id);

    List<WxPayLogVo> findList(@Param("condition") WxPayLogQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") WxPayLogQVo condition);

    int isExit(WxPayLog record);

}