package com.huacainfo.ace.common.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class LimitedResultHandler implements ResultHandler {
	private final List<Object> list;
	private int rowsLimit = Integer.MAX_VALUE;
	private boolean exceedLimit = false;

	public LimitedResultHandler() {
		list = new ArrayList<Object>();
	}

	@SuppressWarnings("unchecked")
	public LimitedResultHandler(ObjectFactory objectFactory) {
		list = objectFactory.create(List.class);
	}

	@SuppressWarnings("unchecked")
	public LimitedResultHandler(ObjectFactory objectFactory, int rowsLimit) {
		list = objectFactory.create(List.class);
		this.rowsLimit = rowsLimit;
	}

	public void handleResult(ResultContext context) {
		list.add(context.getResultObject());
		if (context.getResultCount() >= rowsLimit) {
			context.stop();
			exceedLimit = true;
			throw new RuntimeException("加载记录行数超过限定的记录数：" + rowsLimit);
		}
	}

	public List<Object> getResultList() {
		return list;
	}

	public void setRowsLimit(int rowsLimit) {
		this.rowsLimit = rowsLimit;
	}

	public boolean getExceedLimit() {
		return exceedLimit;
	}

	public int getRowsLimit() {
		return rowsLimit;
	}

}
