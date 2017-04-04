package com.huacainfo.ace.portal.model;

import java.util.Date;

public class Config  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String deptId;
    private String configKey;
    
    private String category;

    private String configName;

    private String configValue;

    private String remark;

    private Date createTime;
    
    private String syid;
    
    

    public String getSyid() {
		return syid;
	}

	public void setSyid(String syid) {
		this.syid = syid;
	}

	public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
    
}