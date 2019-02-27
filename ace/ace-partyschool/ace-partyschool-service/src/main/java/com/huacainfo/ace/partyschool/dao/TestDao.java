package com.huacainfo.ace.partyschool.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.Test;
import com.huacainfo.ace.partyschool.vo.TestQVo;
import com.huacainfo.ace.partyschool.vo.TestVo;

public interface TestDao {

Test selectByPrimaryKey(String id);

int deleteByPrimaryKey(String id);

int insert(Test record);


int updateByPrimaryKey(Test record);


TestVo selectVoByPrimaryKey(String id);

List<TestVo> findList(@Param("condition") TestQVo condition,
    @Param("start") int start,
    @Param("limit") int limit,
    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TestQVo condition);

    int isExit(Test record);

    int updateStatus(@Param("id") String id,@Param("status") String status);


    List<Map<String,Object>> getList(@Param("p")Map<String,Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

 }