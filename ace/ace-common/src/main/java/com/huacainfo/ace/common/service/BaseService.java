package com.huacainfo.ace.common.service;

import java.rmi.RemoteException;

import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;

/**
 * 
 * @author lsj
 *
 * @param <T>
 *            保存数据泛型
 * @param <T1>
 *            分页返回结果泛型
 * @param <T2>
 *            分页查询结果泛型
 */
public interface BaseService<T, T1, T2> {
	public MessageResponse save(T data, UserProp userProp)
			throws RemoteException;

	public SingleResult<T> get(String id) throws RemoteException;

	public PageResult<T1> findListPage(PageParam page, T2 param,
			UserProp userProp) throws RemoteException;
}
