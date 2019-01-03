package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;

import java.io.Serializable;

public class TaaBaseController extends BaseController implements Serializable {
    private static final long serialVersionUID = 1L;
    public Logger logger = LoggerFactory.getLogger(this.getClass());

}
