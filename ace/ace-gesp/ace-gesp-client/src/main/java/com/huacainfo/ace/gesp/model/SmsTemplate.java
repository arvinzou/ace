package com.huacainfo.ace.gesp.model;

public class SmsTemplate implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	*编号   主键 
	*/
    private String id;
	/**
	*短信模板编码[系统内部]  
	*/
	
    private String code;
	/**
	*短信模板编码[百度内部]  
	*/
	
    private String templateCode;
	/**
	*模板名称  
	*/
	
    private String name;
	/**
	*模板内容  
	*/
	
    private String content;
	/**
	*是否可 用[1=可用,0=不可用]  
	*/
	
    private String isEnable;
	/**
	*备注  
	*/
	
    private String remark;
	/**
	*创建时间  
	*/
	
    private java.util.Date createDate;
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
	
    private java.util.Date lastModifyDate;
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
	*获取短信模板编码[系统内部]
	*/
	public  String getCode()
	{
		return code;
	}
    /**
	*设置短信模板编码[系统内部]
	*/
	public  void setCode(String code)
	{
		this.code=code;
	}
	/**
	*获取短信模板编码[百度内部]
	*/
	public  String getTemplateCode()
	{
		return templateCode;
	}
    /**
	*设置短信模板编码[百度内部]
	*/
	public  void setTemplateCode(String templateCode)
	{
		this.templateCode=templateCode;
	}
	/**
	*获取模板名称
	*/
	public  String getName()
	{
		return name;
	}
    /**
	*设置模板名称
	*/
	public  void setName(String name)
	{
		this.name=name;
	}
	/**
	*获取模板内容
	*/
	public  String getContent()
	{
		return content;
	}
    /**
	*设置模板内容
	*/
	public  void setContent(String content)
	{
		this.content=content;
	}
	/**
	*获取是否可 用[1=可用,0=不可用]
	*/
	public  String getIsEnable()
	{
		return isEnable;
	}
    /**
	*设置是否可 用[1=可用,0=不可用]
	*/
	public  void setIsEnable(String isEnable)
	{
		this.isEnable=isEnable;
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
	public  java.util.Date getCreateDate()
	{
		return createDate;
	}
    /**
	*设置创建时间
	*/
	public  void setCreateDate(java.util.Date createDate)
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
	public  java.util.Date getLastModifyDate()
	{
		return lastModifyDate;
	}
    /**
	*设置最后修改时间
	*/
	public  void setLastModifyDate(java.util.Date lastModifyDate)
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
