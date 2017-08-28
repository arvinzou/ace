package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

public class BsSmsTask  implements Serializable {

	/**
	*主键   主键 
	*/
	private String id;
	/**
	*任务名称  
	*/
	private String name;
	/**
	*短信内容  
	*/
	private String content;
	/**
	*创建时间  
	*/
	private java.util.Date createTime;
	/**
	*发送时间  
	*/
	private java.util.Date sendTime;
	/**
	*是否可视  
	*/
	private String isEnable;
	/**
	*机构  
	*/
	private String departmentId;
	/**
	*创建的用户  
	*/
	private String createUserId;
	 
	private String remark;
	
    
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	*获取主键
	*/
	public  String getId()
	{
		return id;
	}
    /**
	*设置主键
	*/
	public  void setId(String id)
	{
		this.id=id;
	}
	/**
	*获取任务名称
	*/
	public  String getName()
	{
		return name;
	}
    /**
	*设置任务名称
	*/
	public  void setName(String name)
	{
		this.name=name;
	}
	/**
	*获取短信内容
	*/
	public  String getContent()
	{
		return content;
	}
    /**
	*设置短信内容
	*/
	public  void setContent(String content)
	{
		this.content=content;
	}
	/**
	*获取创建时间
	*/
	public  java.util.Date getCreateTime()
	{
		return createTime;
	}
    /**
	*设置创建时间
	*/
	public  void setCreateTime(java.util.Date createTime)
	{
		this.createTime=createTime;
	}
	/**
	*获取发送时间
	*/
	public  java.util.Date getSendTime()
	{
		return sendTime;
	}
    /**
	*设置发送时间
	*/
	public  void setSendTime(java.util.Date sendTime)
	{
		this.sendTime=sendTime;
	}
	/**
	*获取是否可视
	*/
	public  String getIsEnable()
	{
		return isEnable;
	}
    /**
	*设置是否可视
	*/
	public  void setIsEnable(String isEnable)
	{
		this.isEnable=isEnable;
	}
	/**
	*获取机构
	*/
	public  String getDepartmentId()
	{
		return departmentId;
	}
    /**
	*设置机构
	*/
	public  void setDepartmentId(String departmentId)
	{
		this.departmentId=departmentId;
	}
	/**
	*获取创建的用户
	*/
	public  String getCreateUserId()
	{
		return createUserId;
	}
    /**
	*设置创建的用户
	*/
	public  void setCreateUserId(String createUserId)
	{
		this.createUserId=createUserId;
	}
		 
		 
       
        
		 
}
