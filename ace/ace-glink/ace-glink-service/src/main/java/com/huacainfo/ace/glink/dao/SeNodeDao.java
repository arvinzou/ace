package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeNode;
import com.huacainfo.ace.glink.vo.SeNodeQVo;
import com.huacainfo.ace.glink.vo.SeNodeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeNodeDao {

    SeNode selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeNode record);

    int updateByPrimaryKey(SeNode record);

    SeNodeVo selectVoByPrimaryKey(String id);

    List<SeNodeVo> findList(@Param("condition") SeNodeQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeNodeQVo condition);

    int isExist(SeNode record);

}
