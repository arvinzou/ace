package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.ZkAttData;
import com.huacainfo.ace.partyschool.vo.ZkAttDataQVo;
import com.huacainfo.ace.partyschool.vo.ZkAttDataVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZkAttDataDao {

    ZkAttData selectByPrimaryKey(String id);

    ZkAttDataVo selectVoByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ZkAttData record);

    int updateByPrimaryKey(ZkAttData record);

    List<ZkAttData> findList(ZkAttData param);

    int findCount(@Param("condition") ZkAttDataQVo condition);

    List<ZkAttDataVo> findVoList(@Param("condition") ZkAttDataQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int clearUp();
}