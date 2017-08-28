package com.huacainfo.ace.gesp.model;

import java.util.Date;

public class ChargingItem implements java.io.Serializable{  
    /**    
     * serialVersionUID:TODO    
     *    
     * @since Ver 1.1    
     */    
	private static final long serialVersionUID = 1L;

    private String id;
    
    private String pid;

    private String memberCode;

    private String name;
    /**
     * 项目分类(会费,培训费,咨询费)
     */
    private String itemType;
    /**
     * 是否按会员等级分类
     */
    private String whetherMemberlevel;
    /**
     * 是否按周期收费
     */
    private String whetherPeriod;

    private Integer year;
    
    private String times;
    
    private String period;
    
    /**
     * 是否按自定义收费
     */
    private String whetherCustom;
    /**
     * 自定义类型
     */
    private String custom;
    
    private String remark;

    private String status;

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
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    /**
     * 获取项目分类
     * @return
     */
    public String getItemType() {
		return itemType;
	}
    /**
     * 设置项目分类
     * @param itemType
     */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getWhetherMemberlevel() {
		return whetherMemberlevel;
	}

	public void setWhetherMemberlevel(String whetherMemberlevel) {
		this.whetherMemberlevel = whetherMemberlevel;
	}

	public String getWhetherPeriod() {
		return whetherPeriod;
	}

	public void setWhetherPeriod(String whetherPeriod) {
		this.whetherPeriod = whetherPeriod;
	}

    public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getWhetherCustom() {
		return whetherCustom;
	}

	public void setWhetherCustom(String whetherCustom) {
		this.whetherCustom = whetherCustom;
	}
	/**
	 * 获取自定义类型
	 * @return
	 */
	public String getCustom() {
		return custom;
	}
	/**
	 * 设置自定义类型
	 * @param custom
	 */
	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
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
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }
}