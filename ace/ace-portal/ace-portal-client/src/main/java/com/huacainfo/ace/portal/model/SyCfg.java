package com.huacainfo.ace.portal.model;

import java.util.Date;

public class SyCfg  implements java.io.Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String name;

    private String logo;

    private String iconCls;

    private String colorCls;

    private Date createDate;

    private String createUserId;

    private String createUserName;

    private Date lastModifyDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getColorCls() {
		return colorCls;
	}

	public void setColorCls(String colorCls) {
		this.colorCls = colorCls;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getLastModifyUserId() {
		return lastModifyUserId;
	}

	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}

	public String getLastModifyUserName() {
		return lastModifyUserName;
	}

	public void setLastModifyUserName(String lastModifyUserName) {
		this.lastModifyUserName = lastModifyUserName;
	}

}