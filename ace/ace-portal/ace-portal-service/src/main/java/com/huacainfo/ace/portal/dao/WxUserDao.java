package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.WxUser;

public interface WxUserDao {
	int deleteByPrimaryKey(String unionId);

	int insert(WxUser record);

	WxUser selectByPrimaryKey(String unionId);

	int updateByPrimaryKey(WxUser record);

	int isExit(WxUser record);
}