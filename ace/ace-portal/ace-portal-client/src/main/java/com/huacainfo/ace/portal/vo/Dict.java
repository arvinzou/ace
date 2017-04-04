package com.huacainfo.ace.portal.vo;

public class Dict implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 字典表名
	 */
	private String tableName;
	/**
	 * 字典名称
	 */
	private String kernelName;
	/**
	 * 自动加载
	 */
	private Boolean autoLoad;
	/**
	 * 加载语句
	 */
	private String loadSql;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getDictName() {
		return kernelName;
	}
	public void setDictName(String kernelName) {
		this.kernelName = kernelName;
	}
	public Boolean getAutoLoad() {
		return autoLoad;
	}
	public void setAutoLoad(Boolean autoLoad) {
		this.autoLoad = autoLoad;
	}
	public String getLoadSql() {
		return loadSql;
	}
	public void setLoadSql(String loadSql) {
		this.loadSql = loadSql;
	}

}
