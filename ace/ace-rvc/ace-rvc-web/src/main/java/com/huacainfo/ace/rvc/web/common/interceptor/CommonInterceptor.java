/**
 * project name:distribution
 * file name:CommonInterceptor
 * package name:com.cdkj.common.interceptor
 * date:2016/9/2 17:31
 * author:haing
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.rvc.web.common.interceptor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * description: 公共拦截器 记录日志 <br>
 * date: 2016/9/2 17:31
 * @author haing
 * @version 1.0
 * @since JDK 1.8
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (logger.isDebugEnabled()) {
            Enumeration<String> keys = request.getParameterNames();
            StringBuffer requestParams = new StringBuffer();
            while (keys.hasMoreElements()) {
                String k = keys.nextElement();
                requestParams.append(k + "=" + request.getParameter(k) + ",");
            }

            logger.debug("-----------------------------------------------------------------------------------");
            logger.debug("Request URL:{}", request.getRequestURL().toString());
            logger.debug("Request REMOTE:{}", getIp(request));
            logger.debug("Request PARAMS:{}", requestParams);
            logger.debug("-----------------------------------------------------------------------------------");
        }

        //设置通过所有跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        return true;
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}