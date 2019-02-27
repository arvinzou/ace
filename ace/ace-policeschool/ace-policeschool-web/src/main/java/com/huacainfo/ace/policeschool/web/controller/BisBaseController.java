package com.huacainfo.ace.policeschool.web.controller;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.security.spring.BasicUsers;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;

import java.io.Serializable;

public class BisBaseController extends BaseController implements Serializable {
    private static final long serialVersionUID = 1L;
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisOperations<String, Object> redisClient;

    public void registerSession(Users syUser) {
        BasicUsers o = new BasicUsers(syUser.getUserId(),
                syUser.getPassword(), syUser.getAccount(),
                syUser.getName(), syUser.getName(),
                syUser.getDepartmentId(), syUser.getCorpName(),
                syUser.getAreaCode(), syUser.getStauts().equals("1"), true,
                true, true, null, null, syUser.getParentCorpId(),
                syUser.getEmail(), syUser.getAccount(), null, null, syUser.getCurSyid(),
                null, syUser.getOpenId(), syUser.getAppOpenId());
        setSessionAttr(CommonKeys.SESSION_USERPROP_KEY, o);
    }

    /**
     * 移除session的值
     *
     * @param key key
     */
    public void delSessionAttr(String key) {
        getRequest().getSession().removeAttribute(key);
    }

    /**
     * 设置session的值
     *
     * @param key   key
     * @param value value
     */
    public void setSessionAttr(String key, Object value) {
        getRequest().getSession().setAttribute(key, value);
    }


    /**
     * 设置redis的值
     *
     * @param key
     * @param value
     * @param time
     */
    public void redisSet(String key, String value, long time) {
        if (time > 0) {
            redisClient.opsForValue().set(key, value, time);
        } else {
            redisClient.opsForValue().set(key, value);
        }
    }

    /**
     * 获取redis的值
     *
     * @param key
     * @return
     */
    public String redisGet(String key) {
        Object value = redisClient.opsForValue().get(key);
        return null == value ? "" : String.valueOf(value);
    }

    /**
     * 删除redis的值
     *
     * @param key
     */
    public void redisDelete(String key) {
        redisClient.delete(key);
    }

    public Userinfo getUserInfo(String unionId) {
        Userinfo userinfo = getCurUserinfo();
        if (StringUtil.isNotEmpty(unionId)) {
            userinfo = new Userinfo();
            userinfo.setUnionid(unionId);
            userinfo.setOpenid(unionId);
            userinfo.setNickname("pcTest");
            userinfo.setSex("1");
        }

        return userinfo;
    }
}
