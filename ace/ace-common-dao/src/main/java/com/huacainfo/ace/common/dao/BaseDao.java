package com.huacainfo.ace.common.dao;

/**
 * 
 * @author lsj
 *
 * @param <T>
 *            实体对象
 */
public interface BaseDao<T> {
	
	public int insert(T entity);

	public int delete(String id);

	public int update(T entity);

	public T get(String id);
	

}
