package com.huacainfo.ace.common.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import com.huacainfo.ace.common.tools.CommonUtils;

public class RedisCommonFlushInteceptor extends RedisCommonBaseInteceptor {
	private static Thread CLEAR_LOCAL_CACHE_THREAD = null;

	public RedisCommonFlushInteceptor() {
		CLEAR_LOCAL_CACHE_THREAD = new ClearLocalCacheThread();
		CLEAR_LOCAL_CACHE_THREAD.start();
	}

	private int localCacheClearInterval = 1 * 60;

	public void setLocalCacheClearInterval(int localCacheClearInterval) {
		this.localCacheClearInterval = localCacheClearInterval;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		result = invocation.proceed();
		String methodName = getMethodName(invocation);
		if (cacheConfig.getRedisKeyFlushPrefixMap().containsKey(methodName)) {
			removeCachedValue(methodName, invocation);
		}
		return result;
	}

	protected void removeCachedValue(String methodName,
			MethodInvocation invocation) throws IllegalArgumentException,
			IllegalAccessException {

		String prefix = cacheConfig.getRedisKeyFlushPrefixMap().get(methodName);
		if (prefix != null) {
			Set<String> keySet = getValueCacheKey(invocation);
			String[] prefixAry = prefix.split(",");
			for (String prefixOne : prefixAry) {
				removeCachedValue(methodName, prefixOne, keySet);
			}
		}

	}

	protected void removeCachedValue(String methodName, String prefix,
			Set<String> keySet) throws IllegalArgumentException,
			IllegalAccessException {
		removeRedisCacheValue(prefix, keySet);
		removeLocalCacheValue(prefix, keySet);
	}

	protected void removeLocalCacheValue(String prefix, Set<String> valueKeySet) {
		if (LOCAL_CACHE.containsKey(prefix)) {
			for (String valueKey : valueKeySet) {
				LOCAL_CACHE.get(prefix).remove(valueKey);
			}
		}
	}

	protected void removeRedisCacheValue(String prefix, Set<String> valueKeySet) {
		StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		Set<String> redisKeySet = getRedisKeySet(prefix, valueKeySet);
		removeRelatePrefixSet(prefix, redisKeySet);
		redisTemplate.delete(redisKeySet);
		clock.stop();
		doExecuteTimeLog("移除", clock.getTotalTimeMillis(), "key数量："
				+ redisKeySet.size());
	}

	private void removeRelatePrefixSet(String prefix, Set<String> redisKeySet) {
		if (cacheConfig.getSaveKeyPrefixSet().contains(prefix)) {
			Set<String> savedKeySet = redisTemplateString.opsForSet().members(
					prefix);
			redisKeySet.addAll(savedKeySet);
			Object[] collValues = CommonUtils.toArrays(redisKeySet);
			redisTemplateString.opsForSet().remove(prefix, collValues);
		}
	}

	private Set<String> getValueCacheKey(MethodInvocation invocation)
			throws IllegalArgumentException, IllegalAccessException {
		ParamCachePos cachePos = getParamCachedPosList(invocation);
		return getCacheValueKey(cachePos, invocation.getArguments());
	}

	private Set<String> getCacheValueKey(ParamCachePos cachePos, Object[] params)
			throws IllegalArgumentException, IllegalAccessException {
		Set<String> keySet = new HashSet<String>(1);
		int i = cachePos.getPos();
		if (params[i] instanceof IRedisMuiltiKey) {
			keySet.addAll(getParamValue(((IRedisMuiltiKey) params[i])
					.redisKeyList()));
		} else if (params[i] instanceof IRedisSingleKey) {
			keySet.add(((IRedisSingleKey) params[i]).redisKey());
		} else {
			keySet.add(String.valueOf(params[i]));
		}
		return keySet;
	}

	protected Set<String> getParamValue(List<Object> keyList)
			throws IllegalArgumentException, IllegalAccessException {
		Set<String> set = new HashSet<String>();
		for (Object key : keyList) {
			set.add(String.valueOf(key));
		}
		return set;
	}

	class ClearLocalCacheThread extends Thread {
		public void run() {
			while (true) {
				try {
					LOCAL_CACHE.clear();
					synchronized (this) {
						this.wait(localCacheClearInterval * 1000);
					}
				} catch (Exception ex) {

				}
			}
		}
	};
}
