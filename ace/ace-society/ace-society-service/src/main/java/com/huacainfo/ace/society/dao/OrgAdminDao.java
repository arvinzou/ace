package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.OrgAdmin;
import com.huacainfo.ace.society.vo.OrgAdminQVo;
import com.huacainfo.ace.society.vo.OrgAdminVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgAdminDao {

    OrgAdmin selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(OrgAdmin record);

    int updateByPrimaryKey(OrgAdmin record);

    OrgAdminVo selectVoByPrimaryKey(String id);

    List<OrgAdminVo> findList(@Param("condition") OrgAdminQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") OrgAdminQVo condition);

    int isExit(OrgAdmin record);

    int updateStatus(OrgAdmin record);

}