package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.CardData;
import com.huacainfo.ace.partyschool.vo.CardDataQVo;
import com.huacainfo.ace.partyschool.vo.CardDataVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CardDataDao {

    CardData selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CardData record);

    int updateByPrimaryKey(CardData record);

    CardDataVo selectVoByPrimaryKey(String id);

    List<CardDataVo> findList(@Param("condition") CardDataQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CardDataQVo condition);

    int isExist(CardData record);

    int updateStatus(CardData record);


    List<Map<String, Object>> findUserList(Map<String, Object> params);

    int updateByUserId(CardDataVo o);
}