package com.huacainfo.ace.gesp.model;

import java.util.Date;

public class Sms implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	*编号   主键 
	*/
	private String id;
	/**
	*百度的模板code  
	*/
	private String templateCode;
	/**
	*手机号码  
	*/
	private String mobile;
	/**
	*发送内容  
	*/
	private String content;
	/**
	*发送时间  
	*/
	private Date sendTime;
	/**
	*发送状态[0=未发送,1=发送成功,2=发送中,3=发送失败.]  
	*/
	private String sendStatus;
	/**
	*发送次数  
	*/
	private Integer sendCount;
	/**
	*备注  
	*/
	private String remark;
	/**
	*创建时间  
	*/
	private Date createDate;
	/**
	*创建的用户ID  
	*/
	private String createUserId;
	/**
	*创建的用户名  
	*/
	private String createUserName;
	/**
	*最后修改时间  
	*/
	private Date lastModifyDate;
	/**
	*最后修改用户ID  
	*/
	private String lastModifyUserId;
	/**
	*最后修改用户名  
	*/
	private String lasttModifyUserName;
	/**
	*反馈ID  
	*/
	private String resultId;
	/**
	*业务id  
	*/
	private String bussId;
    
	/**
	*获取编号
	*/
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
	*获取百度的模板code
	*/
	public  String getTemplateCode()
	{
		return templateCode;
	}
    /**
	*设置百度的模板code
	*/
	public  void setTemplateCode(String templateCode)
	{
		this.templateCode=templateCode;
	}
	/**
	*获取手机号码
	*/
	public  String getMobile()
	{
		return mobile;
	}
    /**
	*设置手机号码
	*/
	public  void setMobile(String mobile)
	{
		this.mobile=mobile;
	}
	/**
	*获取发送内容
	*/
	public  String getContent()
	{
		return content;
	}
    /**
	*设置发送内容
	*/
	public  void setContent(String content)
	{
		this.content=content;
	}
	/**
	*获取发送时间
	*/
	public  Date getSendTime()
	{
		return sendTime;
	}
    /**
	*设置发送时间
	*/
	public  void setSendTime(Date sendTime)
	{
		this.sendTime=sendTime;
	}
	/**
	*获取发送状态[0=未发送,1=发送成功,2=发送中,3=发送失败.]
	*/
	public  String getSendStatus()
	{
		return sendStatus;
	}
    /**
	*设置发送状态[0=未发送,1=发送成功,2=发送中,3=发送失败.]
	*/
	public  void setSendStatus(String sendStatus)
	{
		this.sendStatus=sendStatus;
	}
	/**
	*获取发送次数
	*/
	public  Integer getSendCount()
	{
		return sendCount;
	}
    /**
	*设置发送次数
	*/
	public  void setSendCount(Integer sendCount)
	{
		this.sendCount=sendCount;
	}
	/**
	*获取备注
	*/
	public  String getRemark()
	{
		return remark;
	}
    /**
	*设置备注
	*/
	public  void setRemark(String remark)
	{
		this.remark=remark;
	}
	/**
	*获取创建时间
	*/
	public  Date getCreateDate()
	{
		return createDate;
	}
    /**
	*设置创建时间
	*/
	public  void setCreateDate(Date createDate)
	{
		this.createDate=createDate;
	}
	/**
	*获取创建的用户ID
	*/
	public  String getCreateUserId()
	{
		return createUserId;
	}
    /**
	*设置创建的用户ID
	*/
	public  void setCreateUserId(String createUserId)
	{
		this.createUserId=createUserId;
	}
	/**
	*获取创建的用户名
	*/
	public  String getCreateUserName()
	{
		return createUserName;
	}
    /**
	*设置创建的用户名
	*/
	public  void setCreateUserName(String createUserName)
	{
		this.createUserName=createUserName;
	}
	/**
	*获取最后修改时间
	*/
	public  Date getLastModifyDate()
	{
		return lastModifyDate;
	}
    /**
	*设置最后修改时间
	*/
	public  void setLastModifyDate(Date lastModifyDate)
	{
		this.lastModifyDate=lastModifyDate;
	}
	/**
	*获取最后修改用户ID
	*/
	public  String getLastModifyUserId()
	{
		return lastModifyUserId;
	}
    /**
	*设置最后修改用户ID
	*/
	public  void setLastModifyUserId(String lastModifyUserId)
	{
		this.lastModifyUserId=lastModifyUserId;
	}
	/**
	*获取最后修改用户名
	*/
	public  String getLasttModifyUserName()
	{
		return lasttModifyUserName;
	}
    /**
	*设置最后修改用户名
	*/
	public  void setLasttModifyUserName(String lasttModifyUserName)
	{
		this.lasttModifyUserName=lasttModifyUserName;
	}
	/**
	*获取反馈ID
	*/
	public  String getResultId()
	{
		return resultId;
	}
    /**
	*设置反馈ID
	*/
	public  void setResultId(String resultId)
	{
		this.resultId=resultId;
	}
	/**
	*获取业务id
	*/
	public  String getBussId()
	{
		return bussId;
	}
    /**
	*设置业务id
	*/
	public  void setBussId(String bussId)
	{
		this.bussId=bussId;
	}
	 
}
