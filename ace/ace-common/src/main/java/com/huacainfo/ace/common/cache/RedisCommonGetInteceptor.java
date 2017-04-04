package com.huacainfo.ace.common.cache;

import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class RedisCommonGetInteceptor extends RedisCommonBaseInteceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		String methodName = getMethodName(invocation);
		String valueKey = null;
		String prefix = cacheConfig.getRedisKeyCachedPrefixMap()
				.get(methodName);
		try {
			if (prefix != null) {
				valueKey = getRedisValueKey(methodName, invocation);
				if (valueKey != null) {
					result = getRedisCachedValue(prefix, valueKey);
				}
			}
		} catch (Exception ex) {
			logger.error("获取缓存数据失败", ex);
		}
		if (result == null) {
			result = invocation.proceed();
			try {
				if (valueKey != null && result != null) {
					setCachedValue(prefix, valueKey, result);
				}
			} catch (Exception ex) {
				logger.error("设置缓存数据失败", ex);
			}
		}
		return result;
	}

	protected void setCachedValue(String prefix, String valueKey, Object result) {
		StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		String redisKey = getRedisKey(prefix, valueKey);
		redisTemplate.opsForValue().set(redisKey, result);
		if (cacheConfig.getSaveKeyPrefixSet().contains(prefix)) {
			redisTemplateString.opsForSet().add(prefix, redisKey);
		}
		setLocalCache(prefix, valueKey, result);
		clock.stop();
		doExecuteTimeLog("设置", clock.getTotalTimeMillis(), prefix);
	}

	protected void setLocalCache(String prefixKey, String valueKey, Object value) {
		if (cacheConfig.getLocalCachedPrefixSet().contains(prefixKey)) {
			if (!LOCAL_CACHE.containsKey(prefixKey)) {
				ConcurrentHashMap<String, Object> localCached = new ConcurrentHashMap<String, Object>();
				LOCAL_CACHE.put(prefixKey, localCached);
				localCached.put(valueKey, value);
			} else {
				LOCAL_CACHE.get(prefixKey).put(valueKey, value);
			}
		}
	}

	protected Object getRedisCachedValue(String prefix, String valueKey) {
		StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		Object result = null;
		if (LOCAL_CACHE.containsKey(prefix)) {
			result = LOCAL_CACHE.get(prefix).get(valueKey);
		}
		if (result == null) {
			result = redisTemplate.boundValueOps(getRedisKey(prefix, valueKey))
					.get();
		}
		clock.stop();
		doExecuteTimeLog("获取", clock.getTotalTimeMillis(),
				getRedisKey(prefix, valueKey));
		return result;
	}

	protected String getRedisValueKey(String methodName,
			MethodInvocation invocation) throws IllegalArgumentException,
			IllegalAccessException {
		ParamCachePos cachePos = getParamCachedPosList(invocation);
		String valueKey = null;
		if (cachePos != null) {
			valueKey = getCacheValueKey(cachePos, invocation.getArguments(),
					methodName);
		}
		return valueKey;
	}

	protected String getCacheValueKey(ParamCachePos cachePos, Object[] params,
			String methodName) throws IllegalArgumentException,
			IllegalAccessException {
		String value = getParamValue(cachePos, params[cachePos.getPos()],
				methodName);
		return value;
	}

	protected String getParamValue(ParamCachePos cachePos, Object param,
			String methodName) throws IllegalArgumentException,
			IllegalAccessException {
		Object value = null;
		if (cachePos.getCacheKey() == null
				|| cachePos.getCacheKey().prop() == null) {
			value = param;
		} else {
			String prop = cachePos.getCacheKey().prop();
			value = getObjectProp(param, prop);
		}
		String rString = null;
		if (value instanceof IRedisSingleKey) {
			rString = ((IRedisSingleKey) value).redisKey();
		} else if (checkIsBaseType(value.getClass())) {
			rString = param.toString();
		} else {
			logger.error("获取Key失败，不支持类型的参数作为Key，方法名：{}", methodName);
		}
		return rString;
	}

}
