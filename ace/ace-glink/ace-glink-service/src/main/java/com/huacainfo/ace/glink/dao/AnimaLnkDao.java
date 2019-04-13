package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.AnimaLnk;
import com.huacainfo.ace.glink.vo.AnimaLnkQVo;
import com.huacainfo.ace.glink.vo.AnimaLnkVo;

public interface AnimaLnkDao {

    AnimaLnk selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(AnimaLnk record);

    int updateByPrimaryKey(AnimaLnk record);

    int updateByPrimaryKeySelective(AnimaLnk record);

    AnimaLnkVo selectVoByPrimaryKey(String id);

    List<AnimaLnkVo> findList(@Param("condition") AnimaLnkQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") AnimaLnkQVo condition);

    int isExit(AnimaLnk record);

    int updatePrePlayUrl(@Param("id") String id, @Param("prePlayUrl") String prePlayUrl);
}
