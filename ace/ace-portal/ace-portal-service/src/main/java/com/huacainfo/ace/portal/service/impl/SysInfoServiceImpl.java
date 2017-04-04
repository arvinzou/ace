package com.huacainfo.ace.portal.service.impl;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.huacainfo.ace.portal.service.SysInfoService;

public class SysInfoServiceImpl implements SysInfoService {

	protected String from;
	protected JavaMailSender sender;
	private String subject ;
	private String content;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void sendBatchEmail(String subject, String content,
			List<String> address) throws MessagingException {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		String toList = getMailList(address);
		@SuppressWarnings("static-access")
		InternetAddress[] iaToList = new InternetAddress().parse(toList);
		msg.setRecipients(Message.RecipientType.TO, iaToList);
		helper.setFrom(from);
		helper.setSubject(subject);
		helper.setText(content, true);
		sender.send(msg);
	}

	public String getMailList(List<String> to) {
		StringBuffer toList = new StringBuffer();
		int length = to.size();
		if (to != null && length < 2) {
			toList.append(to.get(0));
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(to.get(i));
				if (i != (length - 1)) {
					toList.append(",");
				}
			}
		}
		return toList.toString();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public JavaMailSender getSender() {
		return sender;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	public boolean sendSMS(String[] mobile, String content) {
		boolean state = true;
		try {
			int i = 0;
			if (mobile != null) {
				for (int j = 0; j < mobile.length; j++) {
					i = this.sendSMS(mobile[j], content);

					if (i == 0) {
						state = true;
					} else {
						state = false;
					}

				}
			}

		} catch (Exception e) {
			state = false;
			e.printStackTrace();
		}

		return state;
	}

	private int sendSMS(String mobile, String content) {

		return 0;
	}

	public static void main(String args[]) {

	}

}
