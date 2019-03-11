package com.huacainfo.ace.light.web.controller;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;

public class LightBaseController extends BaseController {
    private static final long serialVersionUID = 1L;
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisOperations<String, Object> redisClient;

    @Autowired
    private AuthorityService authorityService;

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

    public UserProp parseUser(WxUser user) throws Exception {
        SingleResult<UserProp> u = authorityService.getCurUserPropByOpenId(user.getUnionId());
        return u.getValue();
    }
}
