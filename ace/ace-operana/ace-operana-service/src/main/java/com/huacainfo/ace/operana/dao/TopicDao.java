package com.huacainfo.ace.operana.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.operana.model.Topic;
import com.huacainfo.ace.operana.vo.TopicQVo;
import com.huacainfo.ace.operana.vo.TopicVo;

public interface TopicDao {
    int deleteByPrimaryKey(String TopicId);

    int insert(Topic record);


    Topic selectByPrimaryKey(String TopicId);


    int updateByPrimaryKey(Topic record);
    
    List<TopicVo> findList(@Param("condition") TopicQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TopicQVo condition);

	int isExit(Topic record);

}