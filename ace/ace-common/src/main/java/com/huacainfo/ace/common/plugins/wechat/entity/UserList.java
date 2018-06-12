package com.huacainfo.ace.common.plugins.wechat.entity;

/**
 * Created by HuaCai008 on 2018/6/11.
 */
public class UserList {

    /**
     * 关注该公众账号的总用户数
     */
    private int total;
    /**
     * 拉取的OPENID个数，最大值为10000
     */
    private int count;
    /**
     * 列表数据，OPENID的列表
     */
    private UserOpenIds data;
    /**
     * 拉取列表的最后一个用户的OPENID
     */
    private String next_openid;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UserOpenIds getData() {
        return data;
    }

    public void setData(UserOpenIds data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    class UserOpenIds {
        private String[] openid;

        public String[] getOpenid() {
            return openid;
        }

        public void setOpenid(String[] openid) {
            this.openid = openid;
        }
    }
}
