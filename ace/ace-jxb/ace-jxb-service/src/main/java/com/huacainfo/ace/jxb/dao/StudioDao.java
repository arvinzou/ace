package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.Studio;
import com.huacainfo.ace.jxb.vo.StudioQVo;
import com.huacainfo.ace.jxb.vo.StudioVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudioDao {

    Studio selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Studio record);

    int insertSelective(Studio record);

    int updateByPrimaryKey(Studio record);

    int updateByPrimaryKeySelective(Studio record);

    StudioVo selectVoByPrimaryKey(String id);

    List<StudioVo> findList(@Param("condition") StudioQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") StudioQVo condition);

    List<Studio> isExit(Studio record);

}