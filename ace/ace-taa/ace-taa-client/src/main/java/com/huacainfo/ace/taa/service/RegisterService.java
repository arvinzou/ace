package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;

/**
 * @Auther: Arvin
 * @Date: 2019/1/9 17:58
 * @Description:
 */
public interface RegisterService {
    /**
     * 校验手机号码是否已注册
     *
     * @param mobile 手机号码
     * @return t/f
     */
    boolean isExistByMobile(String mobile);

    /**
     * 注册portal用户
     *
     * @param regType    注册类别
     * @param uid        uid
     * @param openId     openid
     * @param name       昵称
     * @param account    账号
     * @param mobile     手机号码
     * @param sex        性别
     * @param sysId      系统id
     * @param deptId     部门ID
     * @param roleId     角色ID
     * @param acctStatus 账户是否可登陆
     * @param isSendSms  是否发送密码短信提醒
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse insertUsers(String regType,
                                String uid,
                                String openId,
                                String name,
                                String account,
                                String pwd,
                                String mobile,
                                String sex,
                                String sysId,
                                String deptId,
                                String roleId,
                                String acctStatus,
                                boolean isSendSms) throws Exception;

    /**
     * 用户注册
     *
     * @param user   小程序微信用户信息
     * @param name   姓名
     * @param mobile 手机
     * @param copNo  警号
     * @param deptId 所属单位
     * @return ResultResponse
     */
    ResultResponse register(WxUser user, String name, String mobile, String copNo, String deptId) throws Exception;

    /**
     * 发送短信内容
     *
     * @param mobile  手机号码
     * @param content 发送短信内容 ：无需自带短信签名
     * @return MessageResponse
     * @throws Exception
     */
    ResultResponse sendSms(String mobile, String content) throws Exception;
}
