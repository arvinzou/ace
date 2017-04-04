package com.huacainfo.ace.portal.service;

import java.util.List;

import javax.mail.MessagingException;

public interface SysInfoService {
	public abstract void sendBatchEmail(String subject,String content,List<String> address)throws MessagingException;
	public abstract boolean sendSMS(String[] mobile,String content);
}
