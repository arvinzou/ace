package com.huacainfo.ace.jxb.model;

import java.io.Serializable;
import java.util.Date;

public class ConsultOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单主键
     */
    private String id;
    /**
     * 联系方式
     */
    private String tel;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private String sex;
    /**
     * 问题描述标签
     */
    private String tags;
    /**
     * 问题类型及描述
     */
    private String info;
    /**
     * 紧急联系人
     */
    private String secName;
    /**
     * 关系
     */
    private String relationship;
    /**
     * 联系电话
     */
    private String secTel;
    /**
     * 状态
     */
    private String status;
    /**
     * 入库日期
     */
    private Date createDate;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName == null ? null : secName.trim();
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    public String getSecTel() {
        return secTel;
    }

    public void setSecTel(String secTel) {
        this.secTel = secTel == null ? null : secTel.trim();
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
}