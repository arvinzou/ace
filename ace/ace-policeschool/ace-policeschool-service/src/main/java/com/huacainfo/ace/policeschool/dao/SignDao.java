package com.huacainfo.ace.policeschool.dao;

import com.huacainfo.ace.policeschool.vo.AccountVo;
import com.huacainfo.ace.portal.model.Users;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: Arvin
 * @Date: 2019/1/3 10:11
 * @Description:
 */
public interface SignDao {

    int isExistByMobile(@Param("mobile") String mobile);

    int insertReg(@Param("user") Users o,
                  @Param("roleId") String roleId);

    AccountVo findByAcct(String acct);

    Users findByOpenId(@Param("uid") String uid,
                       @Param("sysId") String sysId);

    int bindUserWx(@Param("account") String account,
                   @Param("unionid") String unionid,
                   @Param("sysId") String sysId);

    int updatePwd(@Param("account") String account,
                  @Param("newPwd") String newPwd);

    int updateUsersStatus(@Param("userId") String userId,
                          @Param("status") String status);

    int updateAccount(@Param("userId") String userId,
                      @Param("newAccount") String newAccount);


    int isExistByAccount(@Param("account") String account);

    Users findUsersByMobile(@Param("mobile") String mobile,
                            @Param("sysId") String sysId);
}
