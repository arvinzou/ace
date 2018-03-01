package com.huacainfo.ace.common.result;

import java.util.ArrayList;
import java.util.List;

public class PageResult<T> extends MessageResponse {
	private static final long serialVersionUID = 1L;

	public PageResult(int status, String message) {
		super(status, message);

	}

	public PageResult() {
		super();

	}

	public PageResult(int page, int total, List<T> rows) {
		super();
		setRows(rows);
		setTotal(total);

	}

	/**
	 * 结果列表
	 */
	private List<T> rows = new ArrayList<T>();
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 总记录数
	 */
	private int total;

	/**
	 * 结果列表
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * 结果列表
	 * @param rows
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	
	/**
	 * 总记录数
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 总记录数
	 */
	public void setTotal(int total) {
		this.total = total;
	}

}
