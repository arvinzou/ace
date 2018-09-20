package com.huacainfo.ace.society.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.society.model.CoinConfig;
import com.huacainfo.ace.society.vo.CoinConfigQVo;
import com.huacainfo.ace.society.vo.CoinConfigVo;

public interface CoinConfigDao {

    CoinConfig selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CoinConfig record);


    int updateByPrimaryKey(CoinConfig record);

    int softDel(String id);

    CoinConfigVo selectVoByPrimaryKey(String id);

    List<CoinConfigVo> findList(@Param("condition") CoinConfigQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CoinConfigQVo condition);

    int isExit(CoinConfig record);

    int updateStatus(CoinConfig record);

}