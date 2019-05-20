package com.huacainfo.ace.policeschool.web.filter;

import com.huacainfo.ace.common.security.spring.BasicUsers;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.tools.TimestampKit;
import com.huacainfo.ace.common.web.tools.WebUtils;
import com.huacainfo.ace.policeschool.constant.CommConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebLoginSecurityFilter implements Filter {
    public static final Map<String, String> RESOURCE_AND_ROLE_MAP = new ConcurrentHashMap<>();
    private static Logger LOGGER = LoggerFactory.getLogger(WebLoginSecurityFilter.class);
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private RedisOperations<String, String> redisTemplateString = null;
    private String redirectPage = CommConstant.LOGIN_PAGE;
    private Long localResourceAndRoleClearedTime = 0l;
    private Long localClearInterval = 0l;
    private boolean cachable = true;


    @SuppressWarnings("unchecked")
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (redisTemplateString == null) {
            redisTemplateString = (RedisOperations<String, String>) SpringUtils.getBean("redisTemplateString");
        }

        String loginPage = filterConfig.getInitParameter("loginPage");
        if (StringUtils.isNotBlank(loginPage)) {
            redirectPage = loginPage;
        }

        String interval = filterConfig.getInitParameter("interval");
        if (StringUtils.isNotBlank(interval)) {
            localClearInterval = Integer.parseInt(interval) * 60 * 1000l;
        }
        if (localClearInterval > 0l) {
            cachable = true;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        logger.info("*******************************in WebLoginSecurityFilter*******************************");
        String domain = PropertyUtil.getProperty("fastdfs_server");
        if (domain.endsWith("/")) {
            domain = domain.substring(0, domain.length() - 1);
        }
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession();
        Object object = session.getAttribute(CommonKeys.SESSION_USERPROP_KEY);
        boolean accessable;
        if (object != null) {
            BasicUsers basicUsers = (BasicUsers) object;
            accessable = checkAccessable(httpReq.getRequestURI(), basicUsers);
        } else {
            accessable = false;
        }

        if (accessable) {
            chain.doFilter(request, response);
        } else {
            String uri = domain + redirectPage;
            if (uri.lastIndexOf("?") == -1) {
                uri += "?timestamp=" + TimestampKit.getTimestamp13();
            } else if (uri.lastIndexOf("?") != uri.length() - 1) {
                uri += "&timestamp=" + TimestampKit.getTimestamp13();
            } else {
                uri += "timestamp=" + TimestampKit.getTimestamp13();
            }
            logger.info("WebLoginSecurityFilter.sendRedirect.uri-> {}", uri);
            try {
                ((HttpServletResponse) response).sendRedirect(uri);
                return;
            } catch (IOException e) {
                logger.error("WebLoginSecurityFilter.error-> {}", e);
            }

        }
    }

    private boolean checkAccessable(String uri, BasicUsers basicUsers) {
        String roleString = getRoleStringByUri(uri);
        boolean rst = false;
        if (!StringUtils.isEmpty(roleString)) {
            for (GrantedAuthority grantedAuthority : basicUsers
                    .getAuthorities()) {
                if (roleString.contains("," + grantedAuthority + ",")) {
                    rst = true;
                }
            }
        } else {
            rst = true;
        }
        return rst;
    }

    private String getRoleStringByUri(String uri) {
        checkReflush();
        String roleString = null;
        if (RESOURCE_AND_ROLE_MAP.containsKey(uri)) {
            roleString = RESOURCE_AND_ROLE_MAP.get(uri);
        } else {
            String resourceKey = WebUtils.getRoleResourceRedisKey(uri);
            roleString = redisTemplateString.opsForValue().get(resourceKey);
            if (uri != null && roleString != null) {
                RESOURCE_AND_ROLE_MAP.put(uri, roleString);
            }
        }
        LOGGER.info("{}={}", uri, roleString);
        return roleString;
    }

    private void checkReflush() {
        if (cachable) {
            Calendar calendar = Calendar.getInstance();
            Long currentTime = calendar.getTimeInMillis();
            if (currentTime - localResourceAndRoleClearedTime > localClearInterval) {
                RESOURCE_AND_ROLE_MAP.clear();
                localResourceAndRoleClearedTime = currentTime;
            }
        }
    }

    @Override
    public void destroy() {

    }

}
