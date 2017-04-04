package com.huacainfo.ace.common.monitor;

import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

public class AspireRegexpMethodPointcutAdvisor extends
		RegexpMethodPointcutAdvisor {
	private static final long serialVersionUID = 1L;

	public void setPattern(String pattern) {
		pattern=pattern.trim();
		if (pattern.contains(";")) {
			setPatterns(pattern.split(";"));
		} else if (pattern.contains(",")) {
			setPatterns(pattern.split(","));
		} else {
			setPatterns(pattern);
		}

	}
}
