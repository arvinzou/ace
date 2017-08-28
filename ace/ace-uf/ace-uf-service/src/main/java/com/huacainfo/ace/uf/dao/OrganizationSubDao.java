package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.OrganizationSub;
import com.huacainfo.ace.uf.vo.OrganizationSubQVo;
import com.huacainfo.ace.uf.vo.OrganizationSubVo;

public interface OrganizationSubDao {
    int deleteByPrimaryKey(String OrganizationSubId);

    int insert(OrganizationSub record);


    OrganizationSubVo selectByPrimaryKey(String OrganizationSubId);


    int updateByPrimaryKey(OrganizationSub record);
    
    List<OrganizationSubVo> findList(@Param("condition") OrganizationSubQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") OrganizationSubQVo condition);

	int isExit(OrganizationSub record);

}