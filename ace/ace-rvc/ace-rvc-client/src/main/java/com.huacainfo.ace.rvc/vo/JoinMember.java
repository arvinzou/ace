package com.huacainfo.ace.rvc.vo;

/**
 * Created by Arvin on 2017/12/21.
 */
public class JoinMember {

    /**
     * 用户ID -- 自定义会员时，可以选填
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 邀请人级别 ， 前端传入时，只需处理1,2身份
     * --0- 会议主场，1-普通与会人员，2-特邀嘉宾
     */
    private String level;
    /**
     * 其他备注信息
     */
    private String remark;

    /**
     * 会议修改时使用，true-加入，false-移除
     */
    private boolean isJoin;


    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
