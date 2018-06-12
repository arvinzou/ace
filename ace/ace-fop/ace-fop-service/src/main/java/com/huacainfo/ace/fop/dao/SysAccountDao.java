package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.UserCfg;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.vo.DepartmentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by HuaCai008 on 2018/6/4.
 */
public interface SysAccountDao {

    int insertDepartmentWithDepId(Department department);

    int isExitUsersAccount(String account);

    DepartmentVo selectDepartmentVoByPrimaryKey(String departmentId);

    int insertUsers(@Param("users") Users users);

    int insertUsersRole(@Param("userId") String userId,
                        @Param("roleId") String[] roleId);

    int isExitUserCfg(UserCfg o);

    int updateUserCfgByPKey(UserCfg o);

    int insertUserCfg(UserCfg o);

    List<Map<String, Object>> selectRoleList(@Param("activeSyId") String activeSyId,
                                             @Param("roleTypes") String[] roleTypes);


}
