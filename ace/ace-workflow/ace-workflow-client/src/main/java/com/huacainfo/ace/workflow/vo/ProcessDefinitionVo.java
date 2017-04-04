package com.huacainfo.ace.workflow.vo;

public class ProcessDefinitionVo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String key;
	private String name;
	private String description;
	private int version;
	private String deploymentId;
	private boolean hasStartFormKey;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isHasStartFormKey() {
		return hasStartFormKey;
	}
	public void setHasStartFormKey(boolean hasStartFormKey) {
		this.hasStartFormKey = hasStartFormKey;
	}
	
	
}
