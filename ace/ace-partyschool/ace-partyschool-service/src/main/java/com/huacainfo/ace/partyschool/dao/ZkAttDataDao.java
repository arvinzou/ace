package com.huacainfo.ace.partyschool.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.ZkAttData;
import com.huacainfo.ace.partyschool.vo.ZkAttDataQVo;
import com.huacainfo.ace.partyschool.vo.ZkAttDataVo;

public interface ZkAttDataDao {

    ZkAttData selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ZkAttData record);


    int updateByPrimaryKey(ZkAttData record);


    ZkAttDataVo selectVoByPrimaryKey(String id);

    List<ZkAttDataVo> findList(@Param("condition") ZkAttDataQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ZkAttDataQVo condition);

    int isExit(ZkAttData record);

    int updateStatus(@Param("id") String id, @Param("status") String status);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

}