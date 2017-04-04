package com.huacainfo.ace.workflow.model;

import java.util.Date;

import com.huacainfo.ace.workflow.vo.ProcessDefinitionVo;

public class ActProdefCustom extends ProcessDefinitionVo implements java.io.Serializable{
	
	
      
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;

	

    private String deptId;

    private String categoryId;

    private String areaCode;

    private String remark;

    private String status;

    private Date createDate;

    private String createUserId;

    private String createUserName;

    private Date lastModifyDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

   

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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