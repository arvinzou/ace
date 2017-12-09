package com.huacainfo.ace.rvc.service;

import com.huacainfo.ace.rvc.model.RvcBaseUser;

/**
 * Created by Arvin on 2017/11/23.
 */
public interface UserService {

    /**
     * 1.	从浪潮方获取登录人员参数（我方提供连接地址）
     * 2.	从指定memcache获取登陆人具体信息
     * 3.	人员信息放入session，同步人员数据入库。
     *
     * @param userId 浪潮 userId
     * @return ok/fail
     */
    RvcBaseUser login(String userId);

}
