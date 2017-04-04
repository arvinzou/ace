package com.huacainfo.ace.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * @author lsj
 *
 * @param <T>
 *            参数泛型
 * @param <T1>
 *            结果泛型
 */
public interface BaseQueryDao<T, T1> {
	public PageList<T1> findListPage(PageBounds page, @Param("param") T param);

	public List<T1> findListAll(@Param("param") T param);
}
