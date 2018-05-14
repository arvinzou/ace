package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.portal.vo.UserinfoQVo;
import com.huacainfo.ace.portal.vo.UserinfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserinfoDao {
    int deleteByPrimaryKey(String unionid);

    int insert(Userinfo record);


    UserinfoVo selectByPrimaryKey(String unionid);


    int updateByPrimaryKey(Userinfo record);

    List<UserinfoVo> findList(@Param("condition") UserinfoQVo condition,
                              @Param("start") int start, @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") UserinfoQVo condition);

    int isExit(Userinfo record);

    int updateRole(@Param("unionid") String unionid, @Param("role") String role);

    List<Map<String, Object>> selectWxUser(@Param("condition") Map<String, Object> condition);

    Map<String, Object> selectUserInfoVo(@Param("condition") Map<String, Object> where);
}