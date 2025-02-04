package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.society.vo.CustomerVo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by chenxiaoke on 2018/7/12.
 */
public interface RegDao {

    int insertReg(Users record);

    int isExitByTel(@Param("tel") String tel);


    CustomerVo findByUserId(String userId);

    Userinfo findAdministrator();

    int insertDepartmentWithDepId(Department department);

    int updateUsersDeptId(@Param("users") Users users);
}
