package com.huacainfo.ace.common.security.filter;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.security.core.GrantedAuthority;

import com.huacainfo.ace.common.security.spring.BasicUsers;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;

public class WebAccessDecisionSecurityFilter implements Filter {
	public static final Map<String, String> RESOURCE_AND_ROLE_MAP = new ConcurrentHashMap<String, String>();
	private RedisOperations<String, String> redisTemplateString = null;
	private String redirectPage = "/portal/dynamic/portal/security/login.jsp";

	private Long localResourceAndRoleClearedTime = 0l;
	private Long localClearInterval = 0l;
	private boolean cachable = false;

	@SuppressWarnings("unchecked")
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if(redisTemplateString==null){
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
	public void setRedisTemplateString(RedisOperations<String, String> redisTemplateString){
		this.redisTemplateString=redisTemplateString;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();
		Object object = session.getAttribute(CommonKeys.SESSION_USERPROP_KEY);
		boolean accessable = false;
		if (object != null) {
			BasicUsers basicUsers = (BasicUsers) object;
			accessable = checkAccessable(httpReq.getRequestURI(), basicUsers);
		}else{
			if(httpReq.getRequestURI().indexOf("www")!=-1){
				accessable = true;
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
		if (cachable && RESOURCE_AND_ROLE_MAP.containsKey(uri)) {
			roleString = RESOURCE_AND_ROLE_MAP.get(uri);
		} else {
			String resourceKey = WebUtils.getRoleResourceRedisKey(uri);
			roleString = redisTemplateString.opsForValue().get(resourceKey);
			if (cachable && uri != null && roleString != null) {
				RESOURCE_AND_ROLE_MAP.put(uri, roleString);
			}
		}
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
