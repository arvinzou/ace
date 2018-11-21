package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.PersonInfo;
import com.huacainfo.ace.society.vo.PersonInfoQVo;
import com.huacainfo.ace.society.vo.PersonInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PersonInfoDao {

    PersonInfo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(PersonInfo record);

    int insertSelective(PersonInfo record);

    int updateByPrimaryKey(PersonInfo record);

    int updateByPrimaryKeySelective(PersonInfo record);

    PersonInfoVo selectVoByPrimaryKey(String id);

    List<PersonInfoVo> findList(@Param("condition") PersonInfoQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") PersonInfoQVo condition);

    int addCoin(@Param("list") List list,
                @Param("coinId") String coinId,
                @Param("pCoin") Integer pCoin,
                @Param("pCoin1") Integer pCoin1);

    int addCoinSingle(@Param("userId") String userId,
                      @Param("addCoin") Integer addCoin);

    int isExit(PersonInfo record);

    List<Map<String, Object>> querysocietyOrg(@Param("start") int start,
                                              @Param("limit") int limit,
                                              @Param("condition") Map<String, Object> condition);

    List<Map<String, Object>> queryperson(@Param("start") int start,
                                          @Param("limit") int limit,
                                          @Param("condition") Map<String, Object> condition);
}