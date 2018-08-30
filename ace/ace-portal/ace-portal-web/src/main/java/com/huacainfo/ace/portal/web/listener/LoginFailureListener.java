package com.huacainfo.ace.portal.web.listener;

import com.huacainfo.ace.portal.service.SystemService;
import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

;
/**
 * Created by chenxiaoke on 2018/8/29.
 */
@Component
public class LoginFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static Logger logger = LoggerFactory.getLogger(LoginFailureListener.class);
    @Autowired
    private SystemService systemService;
    //错误了第四次返回true,然后锁定账号,第五次即使密码正确也会报账户锁定
    Set<RequestLimitRule> rules = Collections.singleton(RequestLimitRule.of(10, TimeUnit.MINUTES,3));
    RequestRateLimiter limiter = new InMemorySlidingWindowRequestRateLimiter(rules);
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        if (event.getException().getClass().equals(UsernameNotFoundException.class)) {
            return;
        }
        String account = event.getAuthentication().getName();
        boolean reachLimit = limiter.overLimitWhenIncremented(account);
        logger.info("==================监听到用户{},密码输入错误=======================",account);
        if(reachLimit){
            logger.error("======================用户{},输入密码错误超过三次系统进行用户锁定===========================",account);
            this.systemService.lockUser(account);
        }
    }
}