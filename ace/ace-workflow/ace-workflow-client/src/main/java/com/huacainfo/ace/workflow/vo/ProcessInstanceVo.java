package com.huacainfo.ace.workflow.vo;

import java.util.Date;
import java.util.Map;

public class ProcessInstanceVo implements java.io.Serializable {

	/**
	 * serialVersionUID:TODO
	 * 
	 * @since Ver 1.1
	 */

	private static final long serialVersionUID = 1L;
	
	private String processDefinitionId;
	
	private String processDefinitionName;

	/**
	 * The key of the process definition of the process instance.
	 */
	private String ProcessDefinitionKey;

	/**
	 * The version of the process definition of the process instance.
	 */
	private Integer processDefinitionVersion;

	/**
	 * The deployment id of the process definition of the process instance.
	 */
	private String deploymentId;

	/**
	 * The business key of this process instance.
	 */
	private String businessKey;

	/**
	 * returns true if the process instance is suspended
	 */
	private boolean isSuspended;

	/**
	 * Returns the process variables if requested in the process instance query
	 */
	private Map<String, Object> processVariables;

	/**
	 * The tenant identifier of this process instance
	 */
	private String tenantId;

	/**
	 * Returns the name of this process instance.
	 */
	private String name;

	/**
	 * Returns the description of this process instance.
	 */
	private String description;

	/**
	 * Returns the localized name of this process instance.
	 */
	private String localizedName;

	/**
	 * Returns the localized description of this process instance.
	 */
	private String localizedDescription;

	/**
	 * Returns the start time of this process instance.
	 */
	private Date startTime;

	/**
	 * Returns the user id of this process instance.
	 */
	private String startUserId;

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getProcessDefinitionKey() {
		return ProcessDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		ProcessDefinitionKey = processDefinitionKey;
	}

	public Integer getProcessDefinitionVersion() {
		return processDefinitionVersion;
	}

	public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
		this.processDefinitionVersion = processDefinitionVersion;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}

	public String getLocalizedDescription() {
		return localizedDescription;
	}

	public void setLocalizedDescription(String localizedDescription) {
		this.localizedDescription = localizedDescription;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}
	
	
}
