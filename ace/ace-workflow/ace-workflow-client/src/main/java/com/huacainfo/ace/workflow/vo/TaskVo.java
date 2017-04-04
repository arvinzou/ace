package com.huacainfo.ace.workflow.vo;

import java.util.Map;

public class TaskVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String Id;

	private java.lang.String name;

	private java.lang.String description;

	private java.lang.String assignee;

	private java.util.Date createTime;

	private java.util.Date duedate;

	private java.lang.Integer priority;
	
	private java.lang.String executionId;

	private java.lang.String processInstanceId;

	private java.lang.String processDefinitionId;

	private java.lang.String taskDefinitionKey;

	private java.lang.String formKey;

	Map<String, Object> taskLocalVariables;
	Map<String, Object> trocessVariables;
	public java.lang.String getId() {
		return Id;
	}
	public void setId(java.lang.String id) {
		Id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getDescription() {
		return description;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	public java.lang.String getAssignee() {
		return assignee;
	}
	public void setAssignee(java.lang.String assignee) {
		this.assignee = assignee;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getDuedate() {
		return duedate;
	}
	public void setDuedate(java.util.Date duedate) {
		this.duedate = duedate;
	}
	public java.lang.Integer getPriority() {
		return priority;
	}
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}
	public java.lang.String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(java.lang.String executionId) {
		this.executionId = executionId;
	}
	public java.lang.String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(java.lang.String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public java.lang.String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(java.lang.String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public java.lang.String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(java.lang.String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public java.lang.String getFormKey() {
		return formKey;
	}
	public void setFormKey(java.lang.String formKey) {
		this.formKey = formKey;
	}
	public Map<String, Object> getTaskLocalVariables() {
		return taskLocalVariables;
	}
	public void setTaskLocalVariables(Map<String, Object> taskLocalVariables) {
		this.taskLocalVariables = taskLocalVariables;
	}
	public Map<String, Object> getTrocessVariables() {
		return trocessVariables;
	}
	public void setTrocessVariables(Map<String, Object> trocessVariables) {
		this.trocessVariables = trocessVariables;
	}
	@Override
	public String toString() {
		return "TaskVo [Id=" + Id + ", name=" + name + ", description="
				+ description + ", assignee=" + assignee + ", createTime="
				+ createTime + ", duedate=" + duedate + ", priority="
				+ priority + ", executionId=" + executionId
				+ ", processInstanceId=" + processInstanceId
				+ ", processDefinitionId=" + processDefinitionId
				+ ", taskDefinitionKey=" + taskDefinitionKey + ", formKey="
				+ formKey + ", taskLocalVariables=" + taskLocalVariables
				+ ", trocessVariables=" + trocessVariables + "]";
	}
	
	
}
