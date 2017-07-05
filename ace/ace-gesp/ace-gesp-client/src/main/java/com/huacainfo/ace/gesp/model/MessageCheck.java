package com.huacainfo.ace.gesp.model;

import java.util.Date;

 
public class MessageCheck implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	*编号   主键 
	*/
	  private String id;
	/**
	*消息ID  
	*/
	private String messageId;
	/**
	*接收者id  
	*/
	private String recUserId;
	/**
	*查看次数  
	*/
	private Integer viewTimes;
	/**
	*第一次查看时间  
	*/
	private Date firstViewTime;
	/**
	*最后一次查看时间  
	*/
	private Date lastViewTime;
    
	/**
	*获取编号
	*/
    //(name="Id")
	public  String getId()
	{
		return id;
	}
    /**
	*设置编号
	*/
	public  void setId(String id)
	{
		this.id=id;
	}
	/**
	*获取消息ID
	*/
    //(name="MessageId")
	public  String getMessageId()
	{
		return messageId;
	}
    /**
	*设置消息ID
	*/
	public  void setMessageId(String messageId)
	{
		this.messageId=messageId;
	}
	/**
	*获取接收者id
	*/
    //(name="RecUserId")
	public  String getRecUserId()
	{
		return recUserId;
	}
    /**
	*设置接收者id
	*/
	public  void setRecUserId(String recUserId)
	{
		this.recUserId=recUserId;
	}
	/**
	*获取查看次数
	*/
    //(name="ViewTimes")
	public  Integer getViewTimes()
	{
		return viewTimes;
	}
    /**
	*设置查看次数
	*/
	public  void setViewTimes(Integer viewTimes)
	{
		this.viewTimes=viewTimes;
	}
	/**
	*获取第一次查看时间
	*/
    //(name="FirstViewTime")
	public  Date getFirstViewTime()
	{
		return firstViewTime;
	}
    /**
	*设置第一次查看时间
	*/
	public  void setFirstViewTime(Date firstViewTime)
	{
		this.firstViewTime=firstViewTime;
	}
	/**
	*获取最后一次查看时间
	*/
    //(name="LastViewTime")
	public  Date getLastViewTime()
	{
		return lastViewTime;
	}
    /**
	*设置最后一次查看时间
	*/
	public  void setLastViewTime(Date lastViewTime)
	{
		this.lastViewTime=lastViewTime;
	}
    
}
