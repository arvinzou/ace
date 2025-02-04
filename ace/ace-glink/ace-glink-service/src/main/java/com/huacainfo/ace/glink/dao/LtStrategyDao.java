package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.LtStrategy;
import com.huacainfo.ace.glink.vo.LtStrategyQVo;
import com.huacainfo.ace.glink.vo.LtStrategyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LtStrategyDao {

    LtStrategy selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(LtStrategy record);

    int updateByPrimaryKey(LtStrategy record);

    int updateByPrimaryKeyVo(LtStrategy record);

    LtStrategyVo selectByPrimaryKeyVo(String id);

    List<LtStrategyVo> findList(@Param("condition") LtStrategyQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LtStrategyQVo condition);

    int isExit(LtStrategy record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

    int updateOtherStatus(@Param("id") String id,
                          @Param("status") String status);
}
