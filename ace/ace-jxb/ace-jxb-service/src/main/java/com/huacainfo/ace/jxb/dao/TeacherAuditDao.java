package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.vo.TeacherAuditQVo;
import com.huacainfo.ace.jxb.vo.TeacherAuditVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherAuditDao {

    TeacherAudit selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TeacherAudit record);

    int insertSelective(TeacherAudit record);

    int updateByPrimaryKey(TeacherAudit record);

    int updateByPrimaryKeySelective(TeacherAudit record);

    TeacherAuditVo selectVoByPrimaryKey(String id);

    List<TeacherAuditVo> findList(@Param("condition") TeacherAuditQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TeacherAuditQVo condition);

    int isExit(TeacherAudit record);

    TeacherAudit findByCounselorId(String counselorId);


}