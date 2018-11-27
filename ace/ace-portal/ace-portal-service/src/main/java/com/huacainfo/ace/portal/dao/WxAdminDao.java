package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.WxAdmin;
import com.huacainfo.ace.portal.vo.WxAdminQVo;
import com.huacainfo.ace.portal.vo.WxAdminVo;

public interface WxAdminDao {

    int deleteByPrimaryKey(String id);

    int insert(WxAdmin record);


    int updateByPrimaryKey(WxAdmin record);


    WxAdminVo selectVoByPrimaryKey(String id);

    List<WxAdminVo> findList(@Param("condition") WxAdminQVo condition, @Param("start") int start, @Param("limit") int limit, @Param("orderBy") String orderBy);

    int findCount(@Param("condition") WxAdminQVo condition);

    int isExit(WxAdmin record);

    List<Map<String,Object>> selectList(String nickname);

}