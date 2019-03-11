package com.huacainfo.ace.partyschool.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.Topic;
import com.huacainfo.ace.partyschool.vo.TopicQVo;
import com.huacainfo.ace.partyschool.vo.TopicVo;

public interface TopicDao {

    Topic selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TopicQVo record);


    int updateByPrimaryKey(Topic record);


    TopicVo selectVoByPrimaryKey(String id);

    List<TopicVo> findList(@Param("condition") TopicQVo condition,
                           @Param("start") int start,
                           @Param("limit") int limit,
                           @Param("orderBy") String orderBy);

    List<TopicVo> findTopicFullList(@Param("testId") String testId);

    int findCount(@Param("condition") TopicQVo condition);

    int isExit(Topic record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}