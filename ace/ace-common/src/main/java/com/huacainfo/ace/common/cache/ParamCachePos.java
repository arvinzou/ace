package com.huacainfo.ace.common.cache;

public class ParamCachePos {
	private CacheKey cacheKey = null;
	private int pos;

	public ParamCachePos(CacheKey cacheKey, int pos) {
		this.cacheKey = cacheKey;
		this.pos = pos;
	}

	public ParamCachePos(int pos) {
		this.pos = pos;
	}

	public CacheKey getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(CacheKey cacheKey) {
		this.cacheKey = cacheKey;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

}
