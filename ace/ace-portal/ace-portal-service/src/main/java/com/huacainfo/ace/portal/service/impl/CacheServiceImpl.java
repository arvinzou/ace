package com.huacainfo.ace.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.portal.dao.DictMapper;
import com.huacainfo.ace.portal.dao.SystemDao;
import com.huacainfo.ace.portal.model.Dict;
import com.huacainfo.ace.portal.service.CacheService;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {
	@Autowired
	RedisOperations<String, Object> redisTemplate;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SystemDao systemDao;
	@Autowired
	private DictMapper kernelMapper;
	

	public Object get(String key) {
		logger.debug("get for key ->{}", key);
		return redisTemplate.opsForValue().get(key);
	}

	public void put(String key, Object value) {
		logger.debug("put key ->{}", key);
		redisTemplate.opsForValue().set(key, value);
	}

	public boolean containsKey(String key) {
		return redisTemplate.opsForValue().get(key) == null ? false : true;
	}

	public void clear() {
		init();
	}

	public void init() {
		logger.info("cache init C0001");
		String key = "C0001";
		List<Map<String, String>> list = this.systemDao
				.selectProvinceTreeList();
		logger.debug("C0001.size {}",  list.size());
		this.put(key, list);
		
		
		String C0002 = "C0002";
		String[] categoryId = new String[]{"45","46"};
		
		for(String o : categoryId){
			List<Dict> kernelList = this.kernelMapper.findListByCategoryId(o);
			Map<String, String> map = new HashMap<String, String>();
			for(Dict kernel: kernelList){
				map.put(kernel.getName(), kernel.getCode());
				logger.debug("C0002.map.key {} ->{}", kernel.getName(),kernel.getCode());
			}
			this.put(C0002+"_"+o, map);
		}
		logger.debug("C0002.size {}",  categoryId.length);
		

	}
	

	public CacheServiceImpl() {
	}
}
