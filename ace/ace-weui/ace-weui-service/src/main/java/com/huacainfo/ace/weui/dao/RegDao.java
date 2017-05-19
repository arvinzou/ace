package com.huacainfo.ace.weui.dao;
import com.huacainfo.ace.weui.model.Users;

/**
 * Created by chenxiaoke on 2017/5/16.
 */
public interface RegDao {

    public  int insertUsers(Users users);
    public  Users selectUsersByAccount (String account);
    public int isExitUsersByAccount(String account);
}
