package com.huacainfo.ace.common.result;

import java.util.ArrayList;
import java.util.List;

public class ListResult<T> extends MessageResponse {
	private static final long serialVersionUID = 1L;

	public ListResult(int status, String message) {
		super(status, message);
	}

	public ListResult(int status, String message, String errorStack) {
		super(status, message, errorStack);
	}

	public ListResult() {
		super();
	}

	/**
	 * 其他结果
	 */
	private List<T> value=new ArrayList<T>();

	/**
	 * 其他结果
	 */
	public List<T> getValue() {
		return value;
	}

	/**
	 * 其他结果
	 */
	public void setValue(List<T> value) {
		this.value = value;
	}

}
