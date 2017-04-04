package com.huacainfo.ace.common.security.spring;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.huacainfo.ace.common.service.WebContextParamService;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;

/**
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中，
 * 供Spring Security使用，用于权限校验。
 * 
 * @author sparta 11/3/29
 *
 */

public class CustomFilterSecurityInterceptor extends
		AbstractSecurityInterceptor implements Filter {
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private WebContextParamService webContextParamService;
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		if (httpReq.getSession().getAttribute(CommonKeys.SESSION_USERPROP_KEY) == null) {
			Object obj = SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (obj instanceof BasicUsers) {
				BasicUsers userDetails = (BasicUsers) obj;
				if (userDetails != null) {
					this.logger
							.info("============load UserProp in session===============");
					
					if(this.webContextParamService==null){
						this.webContextParamService=(WebContextParamService)SpringUtils.getBean("systemService");
					}
					String ip = WebUtils.getIpAddr(httpReq);
					userDetails.setIp(ip);
					httpReq.getSession().setAttribute(
							CommonKeys.SESSION_USERPROP_KEY, userDetails);
					@SuppressWarnings("unchecked")
					Map<String,String> cfg=(Map<String,String>)httpReq.getSession().getServletContext().getAttribute(CommonKeys.cfg);
					Map<String,String> map=webContextParamService.loadConfig(userDetails.getActiveSyId());
					Map<String,String> sessionCfg=new HashMap<String,String>();
					sessionCfg.putAll(cfg);
					sessionCfg.putAll(map);
					httpReq.getSession().setAttribute(
							CommonKeys.cfg, sessionCfg);
					logger.info("当前用户信息:{}", userDetails);
					logger.info("当前配置信息:{}", sessionCfg);
				}
			}
		}
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);

	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public void invoke(FilterInvocation fi) throws IOException,
			ServletException {
		InterceptorStatusToken token = super.beforeInvocation(fi);

		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}

	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	public void destroy() {

	}

	public void init(FilterConfig filterconfig) throws ServletException {
		
		
	}

}
