package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.model.Reg;

public interface RegService {
    /**
     * 注册portal.users 登录
     *
     * @param regType 身份标识 1 -- 老师 ，2 - 家长
     * @return
     * @throws Exception
     */
    MessageResponse insertReg(Reg o, String regType) throws Exception;

    /**
     * 判断该号码是否已经注册过
     */
    boolean isExitByTel(String mobile);

    /**
     * 统一注册
     *
     * @param regType  身份标识 1 -- 老师 ，2 - 家长
     * @param mobile   手机号码
     * @param userinfo 微信识别信息
     * @return
     */
    ResultResponse register(String regType, String mobile, Userinfo userinfo) throws Exception;

    /**
     * 查询会员信息
     *
     * @param userinfo
     * @return
     */
    ResultResponse findInfo(Userinfo userinfo) throws Exception;
}
