package com.huacainfo.ace.portal.model;

import java.util.Date;

public class Notice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String noticeId;

	private String category;

	private String title;

	private String publisher;

	private String top;

	private String status;

	private Date createTime;

	private Date publishTime;

	private Date deadline;

	private String departmentId;

	private String groupsId;

	private String content;

	private String syid;

	public String getSyid() {
		return syid;
	}

	public void setSyid(String syid) {
		this.syid = syid;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId == null ? null : noticeId.trim();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher == null ? null : publisher.trim();
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top == null ? null : top.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId == null ? null : departmentId.trim();
	}

	public String getGroupsId() {
		return groupsId;
	}

	public void setGroupsId(String groupsId) {
		this.groupsId = groupsId == null ? null : groupsId.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}