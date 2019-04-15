package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.AmmeterArea;
import com.huacainfo.ace.glink.vo.AmmeterAreaQVo;
import com.huacainfo.ace.glink.vo.AmmeterAreaVo;

public interface AmmeterAreaDao {

    AmmeterArea selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(AmmeterArea record);

    int updateByPrimaryKey(AmmeterArea record);

    AmmeterAreaVo selectVoByPrimaryKey(String id);

    List<AmmeterAreaVo> findList(@Param("condition") AmmeterAreaQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") AmmeterAreaQVo condition);

    int isExit(AmmeterArea record);
}
