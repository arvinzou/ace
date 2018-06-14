package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CuUser;
import com.huacainfo.ace.cu.vo.CuUserQVo;
import com.huacainfo.ace.cu.vo.CuUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CuUserDao {

    CuUser selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CuUser record);

    int insertSelective(CuUser record);

    int updateByPrimaryKey(CuUser record);

    int updateByPrimaryKeySelective(CuUser record);

    CuUserVo selectVoByPrimaryKey(String id);

    List<CuUserVo> findList(@Param("condition") CuUserQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CuUserQVo condition);

    int isExit(CuUser record);


    CuUserVo findByOpenId(String openId);
}