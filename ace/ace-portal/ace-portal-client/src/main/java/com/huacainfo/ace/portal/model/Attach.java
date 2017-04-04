package com.huacainfo.ace.portal.model;

import java.util.Date;

public class Attach  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
    private Integer attachId;

	private String category;

	private String noticeId;

	private String fileUrl;

	private String fileName;

	private String fileSize;

	private Date createTime;

	public Integer getAttachId() {
		return attachId;
	}

	public void setAttachId(Integer attachId) {
		this.attachId = attachId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId == null ? null : noticeId.trim();
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl == null ? null : fileUrl.trim();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize == null ? null : fileSize.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}