package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.result.ResultResponse;

/**
 * @ClassName SysInitService
 * @Description TODO
 * @Author HuaCai008
 * @Date 2019/3/5 9:09
 */
public interface SysInitService {
    /**
     * 初始化新系统
     *
     * @param sysId   系统识别ID
     * @param sysName 系统名称
     * @param acct    管理员账号
     * @param pwd     管理员密码
     * @return ResultResponse 处理结果
     */
    ResultResponse addNewSystem(String sysId, String sysName, String acct, String pwd) throws Exception;
}
