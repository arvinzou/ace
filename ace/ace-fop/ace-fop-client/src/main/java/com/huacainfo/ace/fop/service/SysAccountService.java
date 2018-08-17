package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.model.UserCfg;
import com.huacainfo.ace.portal.model.Users;

import java.util.List;
import java.util.Map;

/**
 * Created by HuaCai008 on 2018/6/4.
 */
public interface SysAccountService {

    /**
     * 新增部门
     *
     * @param department
     * @param userProp
     * @return
     */
    MessageResponse insertDepartment(Department department, UserProp userProp) throws Exception;

    /**
     * 新增系统用户
     *
     * @param users
     * @param userProp
     * @param logTag
     * @return
     */
    ResultResponse insertUsersRecord(Users users, UserProp userProp, String logTag) throws Exception;

    /**
     * 给用户配置分配角色
     *
     * @param userId
     * @param roleId
     * @param userProp
     * @return
     * @throws Exception
     */
    MessageResponse insertUsersRole(String userId, String[] roleId, UserProp userProp) throws Exception;

    /**
     * 给用户设定个性化配置
     *
     * @param list
     * @param userProp
     * @return
     * @throws Exception
     */
    MessageResponse saveOrUpdateUserCfg(List<UserCfg> list, UserProp userProp) throws Exception;

    ResultResponse initSysUser(String deptName, String account, String password, String logTag,
                               UserProp userProp) throws Exception;

    String getAccount(String relationType, String relationId);


    /**
     * 便利工具，彻底销毁账号
     *
     * @param account
     * @return
     */
    ResultResponse destoryAccount(String account);

    Map<String, Object> getAccountInfo(String relationId, String relationType);

    ResultResponse destroyAll();

    /**
     * 数据导入 com_data_import
     */
    ResultResponse dataImport() throws Exception;
}
