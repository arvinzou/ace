package com.huacainfo.ace.common.redis;

import java.util.Collection;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

public class AspireRedisTemplate extends StringRedisTemplate {
	/**
	 * 保存Set
	 * 
	 * @param key
	 * @param coll
	 */
	public void addCollectionToSet(String key, Collection<String> coll) {
		if (coll != null && coll.size() > 0) {
			String[] collValues = new String[coll.size()];
			collValues = coll.toArray(collValues);
			BoundSetOperations<String, String> boundSet = this.boundSetOps(key);
			boundSet.add(collValues);
		}
	}

	/**
	 * 覆盖Set 先刪除再保存
	 * 
	 * @param key
	 * @param coll
	 */
	public void recoverSet(String key, Collection<String> coll) {
		this.delete(key);
		this.addCollectionToSet(key, coll);
	}
}
