package com.huacainfo.ace.gesp.model;

import java.util.Date;

 
public class Message implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	*编号   主键 
	*/
	private String id;
	/**
	*名称  
	*/
	private String name;
	/**
	*内容  
	*/
	private String content;
	/**
	*发送者  
	*/
	private String sender;
	/**
	*状态[0=普通,1=重要,]  
	*/
	private String statusType;
	/**
	*类型类型[2级的]  
	*/
	private String messageType;
	/**
	*排序号  
	*/
	private Integer sortCode;
	/**
	*是否有效[1=有效,0=无效]  
	*/
	private String isEnable;
	/**
	*是否置顶[1=是,0=否]  
	*/
	private String isTop;
	/**
	*扩展列5  
	*/
	private String column5;
	/**
	*扩展列4  
	*/
	private String column4;
	/**
	*扩展列3  
	*/
	private String column3;
	/**
	*扩展列2  
	*/
	private String column2;
	/**
	*扩展列1  
	*/
	private String column1;
	/**
	*过期时间  
	*/
	private Date expireDate;
	/**
	*查看次数  
	*/
	private Integer viewTimes;
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
	*获取名称
	*/
	public  String getName()
	{
		return name;
	}
    /**
	*设置名称
	*/
	public  void setName(String name)
	{
		this.name=name;
	}
	/**
	*获取内容
	*/
	public  String getContent()
	{
		return content;
	}
    /**
	*设置内容
	*/
	public  void setContent(String content)
	{
		this.content=content;
	}
	/**
	*获取发送者
	*/
	public  String getSender()
	{
		return sender;
	}
    /**
	*设置发送者
	*/
	public  void setSender(String sender)
	{
		this.sender=sender;
	}
	/**
	*获取状态[0=普通,1=重要,]
	*/
	public  String getStatusType()
	{
		return statusType;
	}
    /**
	*设置状态[0=普通,1=重要,]
	*/
	public  void setStatusType(String statusType)
	{
		this.statusType=statusType;
	}
	/**
	*获取类型类型[2级的]
	*/
	public  String getMessageType()
	{
		return messageType;
	}
    /**
	*设置类型类型[2级的]
	*/
	public  void setMessageType(String messageType)
	{
		this.messageType=messageType;
	}
	/**
	*获取排序号
	*/
	public  Integer getSortCode()
	{
		return sortCode;
	}
    /**
	*设置排序号
	*/
	public  void setSortCode(Integer sortCode)
	{
		this.sortCode=sortCode;
	}
	/**
	*获取是否有效[1=有效,0=无效]
	*/
	public  String getIsEnable()
	{
		return isEnable;
	}
    /**
	*设置是否有效[1=有效,0=无效]
	*/
	public  void setIsEnable(String isEnable)
	{
		this.isEnable=isEnable;
	}
	/**
	*获取是否置顶[1=是,0=否]
	*/
	public  String getIsTop()
	{
		return isTop;
	}
    /**
	*设置是否置顶[1=是,0=否]
	*/
	public  void setIsTop(String isTop)
	{
		this.isTop=isTop;
	}
	/**
	*获取扩展列5
	*/
	public  String getColumn5()
	{
		return column5;
	}
    /**
	*设置扩展列5
	*/
	public  void setColumn5(String column5)
	{
		this.column5=column5;
	}
	/**
	*获取扩展列4
	*/
	public  String getColumn4()
	{
		return column4;
	}
    /**
	*设置扩展列4
	*/
	public  void setColumn4(String column4)
	{
		this.column4=column4;
	}
	/**
	*获取扩展列3
	*/
	public  String getColumn3()
	{
		return column3;
	}
    /**
	*设置扩展列3
	*/
	public  void setColumn3(String column3)
	{
		this.column3=column3;
	}
	/**
	*获取扩展列2
	*/
	public  String getColumn2()
	{
		return column2;
	}
    /**
	*设置扩展列2
	*/
	public  void setColumn2(String column2)
	{
		this.column2=column2;
	}
	/**
	*获取扩展列1
	*/
	public  String getColumn1()
	{
		return column1;
	}
    /**
	*设置扩展列1
	*/
	public  void setColumn1(String column1)
	{
		this.column1=column1;
	}
	/**
	*获取过期时间
	*/
	public  Date getExpireDate()
	{
		return expireDate;
	}
    /**
	*设置过期时间
	*/
	public  void setExpireDate(Date expireDate)
	{
		this.expireDate=expireDate;
	}
	/**
	*获取查看次数
	*/
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

	
}
