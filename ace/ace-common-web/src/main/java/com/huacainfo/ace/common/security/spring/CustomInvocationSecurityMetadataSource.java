package com.huacainfo.ace.common.security.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * 
 */
@Service("customSecurityMetadataSource")
public class CustomInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private SecurityLoadResouceDefine securityLoadResouceDefine;

	//private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	private AntPathMatcher urlMatcher=new AntPathMatcher();
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<String, Collection<ConfigAttribute>> resource;

	public void loadResourceDefine() {
		this.logger
				.info("================start loadResourceDefine====================");
		// 在Web服务器启动时，提取系统中的所有权限。
		this.resource = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
		List<Map<String, String>> list = securityLoadResouceDefine
				.loadResourceDefine();

		for (Map<String, String> map : list) {
			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
			if (map.get("ROLES") != null) {
				for (String role : map.get("ROLES").split(",")) {
					ConfigAttribute ca = new SecurityConfig(role);
					atts.add(ca);
				}
			}
			logger.debug("资源：{},角色:{}", map.get("RESOURCES"), map.get("ROLES"));
			resource.put(map.get("RESOURCES"), atts);
		}

	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		this.logger
				.info("================start getAllConfigAttributes====================");
		return null;
	}

	// 根据URL，找到相关的权限配置。

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = this.getRequestRi(((FilterInvocation) object)
				.getFullRequestUrl());
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		return getAttributes(url);
	}

	private Collection<ConfigAttribute> getAttributes(String url) {
		Collection<ConfigAttribute> rstAttributes = null;
		logger.info("当前URL：{}", url);
		if (resource.containsKey(url)) {
			rstAttributes = resource.get(url);
		} else {
			Iterator<String> ite = resource.keySet().iterator();
			while (ite.hasNext()) {
				String resURL = ite.next();
				if (urlMatcher.match(url, resURL)) {
					rstAttributes = resource.get(resURL);
				}
			}
		}
		return rstAttributes;
	}

	String getRequestRi(String url) {
		if (url.indexOf("?") != -1) {
			url = url.substring(0, url.indexOf("?"));
		}
		String a = (url.split("//"))[1];
		String b = a.split("/")[0];
		return a.substring(b.length(), a.length());
	}

	public boolean supports(Class<?> arg0) {

		return true;
	}

	public SecurityLoadResouceDefine getSecurityLoadResouceDefine() {
		return securityLoadResouceDefine;
	}

	public void setSecurityLoadResouceDefine(
			SecurityLoadResouceDefine securityLoadResouceDefine) {
		this.securityLoadResouceDefine = securityLoadResouceDefine;
	}

}
