package com.huacainfo.ace.partyschool.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.TopicOpt;
import com.huacainfo.ace.partyschool.vo.TopicOptQVo;
import com.huacainfo.ace.partyschool.vo.TopicOptVo;

public interface TopicOptDao {

TopicOpt selectByPrimaryKey(String id);

int deleteByPrimaryKey(String id);
int deleteTopicOptByTopicId(String id);

int insert(TopicOpt record);


int updateByPrimaryKey(TopicOpt record);


TopicOptVo selectVoByPrimaryKey(String id);

List<TopicOptVo> findList(@Param("condition") TopicOptQVo condition,
    @Param("start") int start,
    @Param("limit") int limit,
    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TopicOptQVo condition);

    int isExit(TopicOpt record);

    int updateStatus(@Param("id") String id,@Param("status") String status);


    List<Map<String,Object>> getList(@Param("p")Map<String,Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);

    int deleteByPrimaryKeys(@Param("ids") String[] ids);

 }