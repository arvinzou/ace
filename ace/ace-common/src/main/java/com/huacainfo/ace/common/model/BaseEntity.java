package com.huacainfo.ace.common.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 标识
	 */
	private String id;
	/**
	 * 地区编码
	 */
	private String areaCode;
	/**
	 * 地区名称
	 */
	private String areaName;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 操作员账号
	 */
	private String userId;
	/**
	 * 操作员名称
	 */
	private String userName;
	/**
	 * 入库日期
	 */
	private Date indate;
	/**
	 * 最后修改用户Id
	 */
	private String updateUserId;
	/**
	 * 最后修改用户名称
	 */
	private String updateUserName;
	/**
	 * 最后修改时间
	 */
	private Date updateTime;

	/**
	 * 标识
	 */
	public String getId() {
		return id;
	}

	/**
	 * 标识
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 地区编码
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * 地区编码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 地区名称
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 地区名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 操作员账号
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 操作员账号
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 操作员名称
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 操作员名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 入库日期
	 */
	public Date getIndate() {
		return indate;
	}

	/**
	 * 入库日期
	 */
	public void setIndate(Date indate) {
		this.indate = indate;
	}

	/**
	 * 最后修改用户Id
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 最后修改用户Id
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * 最后修改用户名称
	 */
	public String getUpdateUserName() {
		return updateUserName;
	}

	/**
	 * 最后修改用户名称
	 */
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	/**
	 * 最后修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 最后修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
}
