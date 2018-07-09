package com.huacainfo.ace.fundtown.dao;

import com.huacainfo.ace.fundtown.model.VipDepartment;
import com.huacainfo.ace.fundtown.vo.VipDepartmentQVo;
import com.huacainfo.ace.fundtown.vo.VipDepartmentVo;
import com.huacainfo.ace.portal.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VipDepartmentDao {

    int insertDepartment(VipDepartment department);

    int updateDepartmentByPrimaryKey(VipDepartment department);

    List<VipDepartmentVo> findDepartmentList(@Param("condition") VipDepartmentQVo condition,
                                             @Param("start") int start,
                                             @Param("limit") int limit,
                                             @Param("orderBy") String orderBy);

    int findDepartmentCount(@Param("condition") VipDepartmentQVo condition);

    VipDepartmentVo selectDepartmentVoByPrimaryKey(@Param("departmentId") String departmentId);

    int delDepartmentByPrimaryKey(@Param("departmentId") String departmentId);

    int isExit(VipDepartment record);

    int isExitEmail(String email);

    int isExitBussLicenseNo(Department record);


    VipDepartmentVo findByMobile(String mobile);
}
