package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.common.tools.JsonUtil;

/**
 * Created by Arvin on 2017/12/28.
 */
public class SocketDTO {
    public static final String ACTION_TEXT = "text";
    public static final String ACTION_FILE = "file";
    public static final String ACTION_IMAGE = "image";
    public static final String ACTION_SPEAKER = "speaker";
    public static final String ACTION_ERROR = "error";
    public static final String ACTION_NOTICE = "notice";

    public SocketDTO() {
    }

    public SocketDTO(String speakerName, String action, Object content, String portrait) {
        this.speakerName = speakerName;
        this.action = action;
        this.content = content;
        this.portrait = portrait;
    }

    public SocketDTO(String rid, String uid, String action, Object content, String portrait) {
        this.rid = rid;
        this.uid = uid;
        this.action = action;
        this.content = content;
        this.portrait = portrait;
    }

    /**
     * 房间ID
     */
    private String rid;
    /**
     * 用户ID
     */
    private String uid;

    /**
     * 发言人姓名
     */
    private String speakerName;
    /**
     * 动作code  --必填
     * text     -- 文本内容
     * file     --  文件 base64 字符串
     * image    -- 图片
     * speaker  -- 申请为发言人
     */
    private String action;
    /**
     * 键入内容 -- 必填
     */
    private Object content;

    /**
     * 人物头像
     */
    private String portrait;

    /**
     * 是否自己
     */
    private boolean isSelf;

    /**
     * 发送为文件时，文件的扩展名
     */
    private String suffix;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
