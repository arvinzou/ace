package com.huacainfo.ace.workflow.model;

import java.util.Date;

public class ActFormItems implements java.io.Serializable{
	
      
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;

	 private String id;

	    private String formId;

	    private String fieldId;

	    private Integer order;

	    private String label;

	    private String fieldType;

	    private Integer row;

	    private Integer colum;

	    private String dataType;

	    private String required;
	    
	    private String dictId;

	    private Date createDate;

	    private String createUserId;

	    private String createUserName;

	    private Date lastModifyDate;

	    private String lastModifyUserId;

	    private String lastModifyUserName;

	    private String status;

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }

	    public String getFormId() {
	        return formId;
	    }

	    public void setFormId(String formId) {
	        this.formId = formId == null ? null : formId.trim();
	    }

	    public String getFieldId() {
	        return fieldId;
	    }

	    public void setFieldId(String fieldId) {
	        this.fieldId = fieldId == null ? null : fieldId.trim();
	    }

	    public Integer getOrder() {
	        return order;
	    }

	    public void setOrder(Integer order) {
	        this.order = order;
	    }

	    public String getLabel() {
	        return label;
	    }

	    public void setLabel(String label) {
	        this.label = label == null ? null : label.trim();
	    }

	    public String getFieldType() {
	        return fieldType;
	    }

	    public void setFieldType(String fieldType) {
	        this.fieldType = fieldType == null ? null : fieldType.trim();
	    }

	    public Integer getRow() {
	        return row;
	    }

	    public void setRow(Integer row) {
	        this.row = row;
	    }

	    public Integer getColum() {
	        return colum;
	    }

	    public void setColum(Integer colum) {
	        this.colum = colum;
	    }

	    public String getDataType() {
	        return dataType;
	    }

	    public void setDataType(String dataType) {
	        this.dataType = dataType == null ? null : dataType.trim();
	    }

	   

	    public String getRequired() {
			return required;
		}

		public void setRequired(String required) {
			this.required = required;
		}

		public String getDictId() {
			return dictId;
		}

		public void setDictId(String dictId) {
			this.dictId = dictId;
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

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status == null ? null : status.trim();
	    }
}