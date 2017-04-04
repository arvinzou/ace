package com.huacainfo.ace.common.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class GzipFilter implements Filter {
	ServletContext sc;
	FilterConfig fc;
	String cacheControl;
	Logger logger = Logger.getLogger(this.getClass());

	public void doFilter(ServletRequest request, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		for (Enumeration<?> e = fc.getInitParameterNames(); e.hasMoreElements();) {
			String headerName = (String) e.nextElement();
			response.addHeader(headerName, fc.getInitParameter(headerName));
		}
		response.addHeader("Content-Encoding", "gzip");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) {
		this.fc = filterConfig;
		this.sc = filterConfig.getServletContext();
		cacheControl = fc.getInitParameter("Cache-Control");
	}

	public void destroy() {
		this.sc = null;
		this.fc = null;
	}
}
