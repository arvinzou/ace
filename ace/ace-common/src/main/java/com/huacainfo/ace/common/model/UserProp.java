package com.huacainfo.ace.common.model;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserProp implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户标识
     */
    private String userId;
    /**
     * 账号
     */
    private String account;

    /**
     * 用户名称
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 所属企业标识
     */
    private String corpId;
    /**
     * 企业名称
     */
    private String corpName;
    /**
     * 所属地区编码
     */
    private String areaCode;
    /**
     * 上级机构编码
     */
    private String parentCorpId;

    private String ip;

    /**
     * 性别(0：未知；1：男；2：女)
     */
    private String sex;

    private String email;

    private String openId;
    private String appOpenId;

    private List<String> roleType;
    private List<String> role;

    private String[] syid;

    private String activeSyId;

    private Map<String, Object> cfg;

//	public Long getUserIdLong() {
//		return Long.valueOf(getUserId());
//	}

    public UserProp() {

    }

    public UserProp(String userId, String name, String nickName, String corpId,
                    String corpName, String areaCode, List<String> roleType,
                    String parentCorpId, List<String> role, String email, String account, String[] syid, String activeSyId, Map<String, Object> cfg, String openId, String appOpenId) {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.corpId = corpId;
        this.corpName = corpName;
        this.areaCode = areaCode;
        this.email = email;
        this.account = account;
        this.parentCorpId = parentCorpId;
        this.role = role;
        this.syid = syid;
        this.roleType = roleType;
        this.activeSyId = activeSyId;
        this.cfg = cfg;
        this.openId = openId;
        this.appOpenId = appOpenId;
    }

    public UserProp(String userId, String name, String nickName, String corpId,
                    String corpName, String areaCode, String ip, List<String> roleType,
                    String parentCorpId, List<String> role, String email, String account, String[] syid, String activeSyId, Map<String, Object> cfg, String openId, String appOpenId) {
        this(userId, name, nickName, corpId, corpName, areaCode, roleType,
                parentCorpId, role, email, account, syid, activeSyId, cfg, openId, appOpenId);
        this.ip = ip;
    }

    /**
     * 用户名称
     */
    public String getName() {
        return name;
    }

    /**
     * 用户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 所属企业标识
     */
    public String getCorpId() {
        return corpId;
    }

    /**
     * 所属企业标识
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    /**
     * 企业名称
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * 企业名称
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 用户标识
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户标识
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 所属地区编码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 所属地区编码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 性别(0：未知；1：男；2：女)
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别(0：未知；1：男；2：女)
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getRoleType() {
        return roleType;
    }

    public void setRoleType(List<String> roleType) {
        this.roleType = roleType;
    }

    public String getParentCorpId() {
        return parentCorpId;
    }

    public void setParentCorpId(String parentCorpId) {
        this.parentCorpId = parentCorpId;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String[] getSyid() {
        return syid;
    }

    public void setSyid(String[] syid) {
        this.syid = syid;
    }

    public String getActiveSyId() {
        return activeSyId;
    }

    public void setActiveSyId(String activeSyId) {
        this.activeSyId = activeSyId;
    }

    public Map<String, Object> getCfg() {
        return cfg;
    }

    public void setCfg(Map<String, Object> cfg) {
        this.cfg = cfg;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAppOpenId() {
        return appOpenId;
    }

    public void setAppOpenId(String appOpenId) {
        this.appOpenId = appOpenId;



    }
}
