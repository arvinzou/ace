
package com.huacainfo.ace.operana.vo;

import com.huacainfo.ace.operana.model.Tpa;


public class TpaVo extends Tpa {
	private static final long serialVersionUID = 1L;

	private String userName;

	private String meetingName;
	private String topicName;
	private String normName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getNormName() {
		return normName;
	}

	public void setNormName(String normName) {
		this.normName = normName;
	}
}
