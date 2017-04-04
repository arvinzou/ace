package com.huacainfo.ace.common.web.tools;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisOperations;

import com.huacainfo.ace.common.tools.CommonKeys;

public class WebUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

	public static String getIpAddr(HttpServletRequest request) {
		 String ip = request.getHeader("x-forwarded-for"); 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       } 
	       return ip; 
	}

	@SuppressWarnings("static-access")
	public static int getDayOfWeek() {
		TimeZone zone = TimeZone.getTimeZone("Asia/Beijing");
		Calendar cal = Calendar.getInstance(zone);

		int c = cal.get(cal.WEEK_OF_YEAR);
		System.out.println(c);
		return c;
	}

	public static void main(String args[]) {
		WebUtils.getDayOfWeek();
	}

	public static void flushRoleResourceCache(
			RedisOperations<String, String> redisTemplateString,
			List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			String resource = map.get("RESOURCES");
			String keyString = getRoleResourceRedisKey(resource);
			redisTemplateString.opsForValue().set(keyString,
					"," + map.get("ROLES") + ",");
		}
		LOGGER.info("加载资源与角色列表到redis缓存成功");
	}

	public static String getRoleResourceRedisKey(String resource) {
		String keyString = CommonKeys.REDIS_RESOURCE_ROLE_PRIFEX + ":"
				+ resource;
		return keyString;
	}
}
