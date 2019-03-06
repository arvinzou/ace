package com.huacainfo.ace.partyschool.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.partyschool.model.TopicOptRst;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.TopicRst;
import com.huacainfo.ace.partyschool.vo.TopicRstQVo;
import com.huacainfo.ace.partyschool.vo.TopicRstVo;

public interface TopicRstDao {

    TopicRst selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TopicRst record);

    int insertTopOptRstList(List<TopicOptRst> list);


    int updateByPrimaryKey(TopicRst record);


    TopicRstVo selectVoByPrimaryKey(String id);

    List<TopicRstVo> findList(@Param("condition") TopicRstQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TopicRstQVo condition);

    int isExit(TopicRst record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}