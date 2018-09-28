package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.BaseModel;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.society.vo.CustomerVo;

/**
 * @Auther: Arvin
 * @Date: 2018/9/19 09:02
 * @Description:
 */
public interface RegService {
    /**
     * 判断该号码是否已经注册过
     */
    boolean isExitByTel(String mobile);

    /**
     * 统一注册
     *
     * @param regType  注册类型 1 -- 个人/党员 ，2 - 社会/党组织
     * @param regInfo  注册信息
     * @param userinfo 微信识别信息
     * @return
     */
    ResultResponse register(String regType, BaseModel regInfo, Userinfo userinfo) throws Exception;

    /**
     * 根据用户ID，查找客户资料
     *
     * @return CustomerVo
     */
    CustomerVo findByUserId(String userId);
}
