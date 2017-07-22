package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.common.model.WxUser;

public interface WxUserDao {
	int deleteByPrimaryKey(String unionId);

	int insert(WxUser record);

	WxUser selectByPrimaryKey(String unionId);

	int updateByPrimaryKey(WxUser record);
	int updateReg(WxUser record);

	int isExit(WxUser record);
}