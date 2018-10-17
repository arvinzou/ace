package com.huacainfo.ace.common.security.filter;

import com.huacainfo.ace.common.security.spring.BasicUsers;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;
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
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebAccessDecisionSecurityFilter implements Filter {
    public static final Map<String, String> RESOURCE_AND_ROLE_MAP = new ConcurrentHashMap<String, String>();
    private static Logger LOGGER = LoggerFactory.getLogger(WebAccessDecisionSecurityFilter.class);
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private RedisOperations<String, String> redisTemplateString = null;
    private String redirectPage = "/portal/dynamic/portal/security/login.jsp";
    private Long localResourceAndRoleClearedTime = 0l;
    private Long localClearInterval = 0l;
    private boolean cachable = true;

    @SuppressWarnings("unchecked")
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (redisTemplateString == null) {
            redisTemplateString = (RedisOperations<String, String>) SpringUtils
                    .getBean("redisTemplateString");
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

    public void setRedisTemplateString(RedisOperations<String, String> redisTemplateString) {
        this.redisTemplateString = redisTemplateString;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession();
        Object object = session.getAttribute(CommonKeys.SESSION_USERPROP_KEY);
        boolean accessable = false;
        redirectPage = "/portal/dynamic/portal/security/login.jsp";
        if (object != null) {
            BasicUsers basicUsers = (BasicUsers) object;
            accessable = checkAccessable(httpReq.getRequestURI(), basicUsers);
        } else {
            if (httpReq.getRequestURI().indexOf("www") != -1) {
                if (httpReq.getRequestURI().indexOf("www/view") != -1) {
                    if (session.getAttribute(CommonKeys.SESSION_USERINFO_KEY) == null) {
                        String appid = PropertyUtil.getProperty("appid");
                        String redirect_uri = PropertyUtil.getProperty("redirect_uri");
                        String scope = PropertyUtil.getProperty("scope");
                        String params = getParamsStr(httpReq);
                        String state = httpReq.getRequestURI() + params + "attach=test";
                        this.logger.info("state ============>{}",state);
                        String encode= URLEncoder.encode(state,"utf-8");
                        this.logger.info("encode ============>{}",encode);
                        redirectPage = this.authorize(appid, redirect_uri, scope, encode);
                        accessable = false;
                    } else {
                        accessable = true;
                    }
                } else {
                    accessable = true;
                }
            }
        }
        if (accessable) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(redirectPage);
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

    public String authorize(String appid, String redirect_uri, String scope, String state) {
        try {
            StringBuffer url = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize");
            url.append("?appid=");
            url.append(appid);
            url.append("&redirect_uri=");
            url.append(URLEncoder.encode(redirect_uri, "utf-8"));
            url.append("&response_type=code");
            url.append("&scope=");
            url.append(scope);
            url.append("&state=");
            url.append(state);
            url.append("#wechat_redirect");
            LOGGER.info("{}", url.toString());
            return url.toString();
        } catch (Exception e) {
            LOGGER.error("{}", e);
            return null;
        }
    }

    protected Map<String, Object> getParamsMap(HttpServletRequest httpReq) {
        Map<String, Object> rst = new HashMap<>();

        Enumeration<String> enu = httpReq.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            rst.put(key, httpReq.getParameter(key));
        }
        this.logger.debug("params:" + rst);
        return rst;
    }

    protected String getParamsStr(HttpServletRequest httpReq) {
        StringBuilder rtnStr = new StringBuilder();
        rtnStr.append("?");

        Enumeration<String> enu = httpReq.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            rtnStr.append(key + "=" + httpReq.getParameter(key)).append("&");
        }
        this.logger.debug("params:" + rtnStr.toString());

        return rtnStr.toString();
    }

    public static void main(String args[]) throws Exception{

       String url= URLEncoder.encode("/live/www/view/live/index.jsp?companyId=00010001&id=90f5c89a-2af1-4485-96f1-2577ac3b8bee&attach=test","utf-8");
       System.out.println(url);

    }

}
