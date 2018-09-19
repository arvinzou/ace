package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class SocietyBaseController extends BaseController implements Serializable {
    private static final long serialVersionUID = 1L;
    public Logger logger = LoggerFactory.getLogger(this.getClass());


    public Userinfo getUserInfo(String unionId) {
        Userinfo userinfo = getCurUserinfo();
        if (StringUtil.isNotEmpty(unionId)) {
            userinfo = new Userinfo();
            userinfo.setUnionid(unionId);
            userinfo.setOpenid(unionId);
            userinfo.setNickname("societyTest");
            userinfo.setSex("1");
        }

        return userinfo;
    }
}
