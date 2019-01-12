package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class TaaBaseController extends BaseController implements Serializable {
    private static final long serialVersionUID = 1L;
    public Logger logger = LoggerFactory.getLogger(this.getClass());


    public UserProp parseUser(WxUser user) {
        UserProp u = new UserProp();
        u.setUserId(user.getUnionId());
        u.setName(user.getNickName());

        return u;
    }
}
