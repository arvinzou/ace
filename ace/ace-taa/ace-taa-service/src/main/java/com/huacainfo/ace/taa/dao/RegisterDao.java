package com.huacainfo.ace.taa.dao;

import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.taa.vo.CustomerVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/10 10:07
 * @Description:
 */
public interface RegisterDao {
    /***
     * portal.users 注册
     * @param o 用户数据
     * @param roleId 角色ID
     * @return int
     */
    int insertUsers(@Param("user") Users o,
                    @Param("roleId") String roleId);

    /**
     * 判断手机号是否已存在
     *
     * @param mobile 手机号码
     * @return int
     */
    int isExistByMobile(String mobile);

    /**
     * 修改用户手机号码
     *
     * @param userId 用户ID
     * @param mobile 新手机
     * @return int
     */
    int updateMobile(@Param("userId") String userId,
                     @Param("mobile") String mobile);

    /**
     * 修改账户密码
     *
     * @param account 账户
     * @param newPwd  新密码
     * @return int
     */
    int updatePwd(@Param("account") String account,
                  @Param("newPwd") String newPwd);

    /**
     * 修改用户状态
     *
     * @param userId 用户ID
     * @param status 状态值
     * @return int
     */
    int updateUsersStatus(@Param("userId") String userId,
                          @Param("status") String status);

    /**
     * 获取用户信息
     *
     * @param condition 条件查询
     * @return CustomerVo
     */
    CustomerVo findCustomerVo(Map<String, Object> condition);
}
