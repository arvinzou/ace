package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.AnimaRes;
import com.huacainfo.ace.glink.vo.AnimaResQVo;
import com.huacainfo.ace.glink.vo.AnimaResVo;

public interface AnimaResDao {

    AnimaRes selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(AnimaRes record);

    int updateByPrimaryKey(AnimaRes record);

    AnimaResVo selectVoByPrimaryKey(String id);

    List<AnimaResVo> findList(@Param("condition") AnimaResQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") AnimaResQVo condition);

    int isExit(AnimaRes record);

    int updateStatus(@Param("id") String id, @Param("status") String status);
}
