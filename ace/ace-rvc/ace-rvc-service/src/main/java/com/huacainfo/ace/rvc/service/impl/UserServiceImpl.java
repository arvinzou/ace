package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.rvc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Arvin on 2017/11/23.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * 1.	从浪潮方获取登录人员参数（我方提供连接地址）
     * 2.	从指定memcache获取登陆人具体信息
     * 3.	人员信息放入session，同步人员数据入库。
     *
     * @return ok/fail
     */
    @Override
    public String login(String userId) {


        return null;
    }

}
