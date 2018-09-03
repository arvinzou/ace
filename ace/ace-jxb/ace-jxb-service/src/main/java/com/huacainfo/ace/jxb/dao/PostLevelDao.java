package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.PostLevel;
import com.huacainfo.ace.jxb.vo.PostLevelQVo;
import com.huacainfo.ace.jxb.vo.PostLevelVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PostLevelDao {

    PostLevel selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(PostLevel record);

    int insertSelective(PostLevel record);

    int updateByPrimaryKey(PostLevel record);

    int updateByPrimaryKeySelective(PostLevel record);

    PostLevelVo selectVoByPrimaryKey(String id);

    List<PostLevelVo> findList(@Param("condition") PostLevelQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") PostLevelQVo condition);

    int isExit(PostLevel record);

    /**
     * 考核业绩报表
     *
     * @param params
     * @return list
     */
    List<Map<String, Object>> examine(Map<String, Object> params);

    List<PostLevel> findConfig();

    PostLevel getLowestPost();
}