package com.huacainfo.ace.common.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;

import com.huacainfo.ace.common.aop.BaseAopAround;
import com.huacainfo.ace.common.tools.CommonUtils;

public abstract class RedisCommonBaseInteceptor extends BaseAopAround {
	protected static Map<String, ConcurrentHashMap<String, Object>> LOCAL_CACHE = new ConcurrentHashMap<String, ConcurrentHashMap<String, Object>>();
	protected static final Map<Method, ParamCachePos> PARAM_CACHE_POS_MAP = new ConcurrentHashMap<Method, ParamCachePos>();
	@Resource(name = "redisTemplate")
	protected RedisOperations<String, Object> redisTemplate;
	@Resource(name = "redisTemplateString")
	protected RedisOperations<String, String> redisTemplateString;
	@Autowired
	protected CacheConfig cacheConfig;
	 

	public RedisCommonBaseInteceptor() {
		super();
		infoExceedMillsecond = 20;
		warnExceedMillsecond = 50;
		errorExceedMillsecond = 100;
	}

	protected ParamCachePos getParamCachedPosList(MethodInvocation invocation) {
		Method method = invocation.getMethod();
		ParamCachePos cachePos = null;
		if (!PARAM_CACHE_POS_MAP.containsKey(method)) {
			Annotation[][] annotations = method.getParameterAnnotations();
			cachePos = getParamCachedPos(annotations);
			if (cachePos == null) {
				cachePos = getFirstParamCachePos(method.getParameterTypes());
			}
			PARAM_CACHE_POS_MAP.put(method, cachePos);
		} else {
			cachePos = PARAM_CACHE_POS_MAP.get(method);
		}
		return cachePos;
	}

	private ParamCachePos getFirstParamCachePos(Class<?>[] paramTypes) {
		ParamCachePos cachePos = null;
		for (int i = 0; i < paramTypes.length; i++) {
			if (checkIsBaseType(paramTypes[i])
					|| IRedisSingleKey.class.isAssignableFrom(paramTypes[i])
					|| IRedisMuiltiKey.class.isAssignableFrom(paramTypes[i])) {
				cachePos = new ParamCachePos(i);
				break;
			}

		}
		return cachePos;
	}

	protected boolean checkIsBaseType(Class<?> cls) {
		boolean rst = false;
		switch (cls.getName()) {
		case "java.lang.Integer":
		case "java.lang.Long":
		case "java.lang.String":
			rst = true;
			break;
		default:
		}
		return rst;
	}

	private ParamCachePos getParamCachedPos(Annotation[][] annotations) {
		ParamCachePos cachePos = null;
		for (int i = 0; i < annotations.length; i++) {
			CacheKey cacheKey = getParamCachedPos(annotations[i]);
			if (cacheKey != null) {
				cachePos = new ParamCachePos(cacheKey, i);
				break;
			}
		}
		return cachePos;
	}

	private CacheKey getParamCachedPos(Annotation[] annotations) {
		if (annotations != null) {
			for (Annotation annotation : annotations) {
				if (annotation instanceof CacheKey) {
					return (CacheKey) annotation;
				}
			}
		}
		return null;
	}

	protected void doExecuteTimeLog(String title, Long executeTime, String key) {
		if (executeTime <= infoExceedMillsecond) {
			logger.debug("{}redisKey：{},执行时长：{}", title, key, executeTime);
		} else if (executeTime <= warnExceedMillsecond) {
			logger.info("{}redisKey：{},执行时长：{}", title, key, executeTime);
		} else if (executeTime <= errorExceedMillsecond) {
			logger.warn("{}redisKey：{},执行时长：{}", title, key, executeTime);
		} else {
			logger.error("{}redisKey：{},执行时长：{}", title, key, executeTime);
		}
	}

	protected void doExecuteTimeLog(String title, Long executeTime,
			Collection<String> keyList) {
		if (executeTime <= infoExceedMillsecond) {
			logger.debug("{},key数量：{},执行时长：{}", title, keyList.size(),
					executeTime);
		} else if (executeTime <= warnExceedMillsecond) {
			logger.info("{},key数量：{},执行时长：{}", title, keyList.size(),
					executeTime);
		} else if (executeTime <= errorExceedMillsecond) {
			logger.warn("{},key数量：{},执行时长：{}", title, keyList.size(),
					executeTime);
		} else {
			logger.error("{},key数量：{},执行时长：{}", title, keyList.size(),
					executeTime);
		}
	}

	protected String getRedisKey(String prefix, String valueKey) {
		return prefix + ":" + valueKey;
	}

	protected Set<String> getRedisKeySet(String prefix, Set<String> valueKeySet) {
		Set<String> redisKeySet = new HashSet<String>(valueKeySet.size());
		for (String valueKey : valueKeySet) {
			redisKeySet.add(getRedisKey(prefix, valueKey));
		}
		return redisKeySet;
	}

	// protected String[] parseRedisKey(String redisKey) {
	// return redisKey.split(":");
	// }

	protected Object getObjectProp(Object obj, String prop)
			throws IllegalArgumentException, IllegalAccessException {
		Object result = null;
		result = CommonUtils.getPropValue(obj, prop);
		return result;
	}
}
