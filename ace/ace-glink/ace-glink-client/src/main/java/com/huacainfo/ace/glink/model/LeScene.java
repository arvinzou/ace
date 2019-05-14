package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：le_scene
 * 注释：弱电场景管理
 *
 * @author HuaCai008
 * @date 2019-05-13
 */
public class LeScene extends BaseModel {
    /**
     * 字段名：le_scene.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：le_scene.sceneNum
     * 注释：场景编号
     */
    private String sceneNum;

    /**
     * 字段名：le_scene.sceneName
     * 注释：场景名称
     */
    private String sceneName;

    /**
     * 字段名：le_scene.sceneDepict
     * 注释：场景描述
     */
    private String sceneDepict;

    /**
     * 字段名：le_scene.viewUrl
     * 注释：预览图片地址
     */
    private String viewUrl;

    /**
     * 字段名：le_scene.playUrl
     * 注释：播放地址
     */
    private String playUrl;

    /**
     * 字段名：le_scene.playStatus
     * 注释：播放状态
     */
    private String playStatus;

    /**
     * 字段名：le_scene.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：le_scene.status
     * 注释：状态
     */
    private String status;

    /**
     * 字段名：le_scene.createDate
     * 注释：创建日期
     */
    private Date createDate;

    /**
     * 字段名：le_scene.updateDate
     * 注释：修改日期
     */
    private Date updateDate;

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSceneNum() {
        return sceneNum;
    }

    public void setSceneNum(String sceneNum) {
        this.sceneNum = sceneNum == null ? null : sceneNum.trim();
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    public String getSceneDepict() {
        return sceneDepict;
    }

    public void setSceneDepict(String sceneDepict) {
        this.sceneDepict = sceneDepict == null ? null : sceneDepict.trim();
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl == null ? null : viewUrl.trim();
    }

    public String getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(String playStatus) {
        this.playStatus = playStatus == null ? null : playStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
