package com.huacainfo.ace.common.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;

public class CacheConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 需要保存Key列表的key前缀
	 */
	private Set<String> saveKeyPrefixSet = new ConcurrentHashSet<String>();
	/**
	 * cached过滤方法与key前缀的映射关系
	 */
	private Map<String, String> redisKeyCachedPrefixMap = new ConcurrentHashMap<String, String>();
	/**
	 * 需要做本地缓存的key前缀
	 */
	private Set<String> localCachedPrefixSet = new ConcurrentHashSet<String>();
	/**
	 * Flush过滤方法与key前缀的映射关系
	 */
	private Map<String, String> redisKeyFlushPrefixMap = new ConcurrentHashMap<String, String>();

	/**
	 * 需要保存Key列表的key前缀
	 */
	public Set<String> getSaveKeyPrefixSet() {
		return saveKeyPrefixSet;
	}

	/**
	 * cached过滤方法与key前缀的映射关系
	 */
	public Map<String, String> getRedisKeyCachedPrefixMap() {
		return redisKeyCachedPrefixMap;
	}

	/**
	 * 需要做本地缓存的key前缀
	 */
	public Set<String> getLocalCachedPrefixSet() {
		return localCachedPrefixSet;
	}

	/**
	 * Flush过滤方法与key前缀的映射关系
	 */
	public Map<String, String> getRedisKeyFlushPrefixMap() {
		return redisKeyFlushPrefixMap;
	}

	/**
	 * 需要保存Key列表的key前缀
	 */
	public void setSaveKeyPrefixSet(Collection<String> saveKeyPrefixSet) {
		this.saveKeyPrefixSet.addAll(saveKeyPrefixSet);
	}

	/**
	 * cached过滤方法与key前缀的映射关系
	 */
	public void setRedisKeyCachedPrefixMap(
			Map<String, String> redisKeyCachedPrefixMap) {
		this.redisKeyCachedPrefixMap.putAll(redisKeyCachedPrefixMap);
	}

	/**
	 * 需要做本地缓存的key前缀
	 */
	public void setLocalCachedPrefixSet(Collection<String> localCachedPrefixSet) {
		this.localCachedPrefixSet.addAll(localCachedPrefixSet);
	}

	/**
	 * Flush过滤方法与key前缀的映射关系
	 */
	public void setRedisKeyFlushPrefixMap(
			Map<String, String> redisKeyFlushPrefixMap) {
		this.redisKeyFlushPrefixMap.putAll(redisKeyFlushPrefixMap);
	}

	public Set<String> getCachedMethodSet() {
		return redisKeyCachedPrefixMap.keySet();
	}
	public Set<String> getFlushMethodSet() {
		return redisKeyFlushPrefixMap.keySet();
	}
}
