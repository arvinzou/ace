package com.huacainfo.ace.uf.model;

import java.util.Date;

/**
 * 奖惩情况实体类
 *
 * @author huaguang_wangen
 */
public class interest implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private String id;
    /**
     * 统战人士编号
     */
    private String personageId;
    /**
     * 兴趣爱好编号
     */
    private String beetId;
    /**
     * 创建人编号
     */
    private String createUserId;
    /**
     * 创建人姓名
     */
    private String createUserName;
    /**
     * 入库时间
     */
    private Date createDate;
    /**
     * 最后更新人编号
     */
    private String lastModifyUserId;
    /**
     * 最后更新人姓名
     */
    private String lastModifyUserName;
    /**
     * 最后更新时间
     */
    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonageId() {
        return personageId;
    }

    public void setPersonageId(String personageId) {
        this.personageId = personageId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }


    public String getBeetId() {
        return beetId;
    }

    public void setBeetId(String beetId) {
        this.beetId = beetId;
    }

    @Override
    public String toString() {
        return "interest{" +
                "id='" + id + '\'' +
                ", personageId='" + personageId + '\'' +
                ", beetId='" + beetId + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", createDate=" + createDate +
                ", lastModifyUserId='" + lastModifyUserId + '\'' +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                ", lastModifyDate=" + lastModifyDate +
                '}';
    }
}
