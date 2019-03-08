package com.huacainfo.ace.partyschool.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.TestRst;
import com.huacainfo.ace.partyschool.vo.TestRstQVo;
import com.huacainfo.ace.partyschool.vo.TestRstVo;

public interface TestRstDao {

    TestRst selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TestRst record);




    int updateByPrimaryKey(TestRst record);


    TestRstVo selectVoByPrimaryKey(String id);

    List<TestRstVo> findList(@Param("condition") TestRstQVo condition,
                             @Param("start") int start,
                             @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TestRstQVo condition);

    int isExit(TestRst record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    int computScore(String id);

}