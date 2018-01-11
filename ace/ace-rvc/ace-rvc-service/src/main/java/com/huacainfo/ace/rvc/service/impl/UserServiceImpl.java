package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.rvc.dao.RvcBaseUserDao;
import com.huacainfo.ace.rvc.kedapi.authorize.AuthorizeApi;
import com.huacainfo.ace.rvc.model.RvcBaseUser;
import com.huacainfo.ace.rvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Arvin on 2017/11/23.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private RvcBaseUserDao rvcBaseUserDao;

    /**
     * 1.	从浪潮方获取登录人员参数（我方提供连接地址）
     * 2.	从指定memcache获取登陆人具体信息
     * 3.	人员信息放入session，同步人员数据入库。
     *
     * @param userId 浪潮 userId
     * @return ok/fail
     */
    @Override
    public RvcBaseUser login(String userId) {
        RvcBaseUser user = rvcBaseUserDao.getByUserId(userId);
        if (null == user) {
            return singleSignOn(userId);
        }

        user.setKedaServerDomain(AuthorizeApi.IP_PORT);
        user.setKedaPwd(AuthorizeApi.ADMIN_PWD);
        return user;
    }

    /***
     * 获取所有人员列表
     * @param userId 操作用户ID
     * @return list
     */
    @Override
    public List<RvcBaseUser> getAll(String userId) {
        return rvcBaseUserDao.getAll();
    }

    /**
     * redis方式获取用户信息
     *
     * @param userId 用户ID-rvc_base_user.userId
     * @return RvcBaseUser
     */
    @Override
    public RvcBaseUser getByUserIdWithRedis(String userId) {
        //TODO 接入Redis功能
        return rvcBaseUserDao.getByUserId(userId);
    }

    /**
     * 从浪潮方获取登录人员信息
     *
     * @param userId 浪潮 userId
     * @return
     */
    private RvcBaseUser singleSignOn(String userId) {
        //session共享，登录信息获取

        //人员资料入库
        RvcBaseUser user = new RvcBaseUser();
        user.setId(GUIDUtil.getGUID());//唯一key
        user.setUserId(userId);
        user.setCreateUserId("0000");
        user.setCreateUserName("system");
        user.setCreateDate(DateUtil.getNowDate());
        user.setLastModifyDate(DateUtil.getNowDate());
        //入库人员资料
        rvcBaseUserDao.insertSelective(user);

        return user;
    }

}
