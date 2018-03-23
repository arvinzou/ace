package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.CaseAudit;
import com.huacainfo.ace.woc.vo.CaseAuditQVo;
import com.huacainfo.ace.woc.vo.CaseAuditVo;

public interface CaseAuditDao {

    CaseAuditVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CaseAudit record);

    int insertSelective(CaseAudit record);

    int updateByPrimaryKey(CaseAudit record);

    int updateByPrimaryKeySelective(CaseAudit record);

    
    List<CaseAuditVo> findList(@Param("condition") CaseAuditQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") CaseAuditQVo condition);

	int isExit(CaseAudit record);

}