package com.huacainfo.ace.glink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.glink.model.SeGatewayState;
import com.huacainfo.ace.glink.vo.SeGatewayStateQVo;
import com.huacainfo.ace.glink.vo.SeGatewayStateVo;

public interface SeGatewayStateDao {

    SeGatewayState selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeGatewayState record);

    int updateByPrimaryKey(SeGatewayState record);

    SeGatewayStateVo selectVoByPrimaryKey(String id);

    List<SeGatewayStateVo> findList(@Param("condition") SeGatewayStateQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeGatewayStateQVo condition);

    int isExit(SeGatewayState record);

    int deleteByPrimaryKeys(@Param("list") List<String> list);

    int allClear();
}
