package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.OrgAdmin;
import com.huacainfo.ace.society.vo.OrgAdminQVo;
import com.huacainfo.ace.society.vo.OrgAdminVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    int isExist(OrgAdmin record);

    int updateStatus(OrgAdmin record);

    OrgAdminVo findByOrgId(String orgId);

    int deleteByOrgId(String orgId);

    List<Map<String, Object>> findAdminList(@Param("keyword") String keyword);

    OrgAdminVo findByUserId(String userId);
}