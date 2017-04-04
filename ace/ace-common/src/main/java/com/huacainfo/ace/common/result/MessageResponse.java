package com.huacainfo.ace.common.result;

import java.util.HashMap;
import java.util.Map;

public class MessageResponse implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 状态：0-成功，1-失败，2-会话超时，其它另行定义
	 */
	private int status;
	String errorMessage;

	String detail;

	public MessageResponse() {

	}

	public MessageResponse(int status, String message) {
		this.status = status;
		this.errorMessage = message;
	}

	public MessageResponse(int status, String message, String errorStack) {
		this(status, message);
		this.detail = errorStack;
	}

	private Map<String, Object> other = new HashMap<String, Object>();
	private Map<String, Object> globle = new HashMap<String, Object>();

	@Deprecated
	public boolean getState() {
		return status == 0;
	}

	@Deprecated
	public void setState(boolean state) {
		if (!state) {
			status = 1;
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Map<String, Object> getOther() {
		return other;
	}

	public void setOther(Map<String, Object> other) {
		this.other = other;
	}

	public Map<String, Object> getGloble() {
		return globle;
	}

	public void setGloble(Map<String, Object> globle) {
		this.globle = globle;
	}

	/**
	 * 状态：0-成功，1-失败，2-会话超时，其它另行定义
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 状态：0-成功，1-失败，2-会话超时，其它另行定义
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
