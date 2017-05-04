package com.huacainfo.ace.portal.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.UserCfg;
import com.huacainfo.ace.portal.service.UserCfgService;
import com.huacainfo.ace.portal.vo.UserCfgVo;
import com.huacainfo.ace.portal.vo.UserCfgQVo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userCfg")
public class UserCfgController extends PortalBaseController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserCfgService userCfgService;


    @RequestMapping(value = "/selectUserCfgByUserId.do")
    @ResponseBody
    public Map<String, Object> selectUserCfgByUserId()
            throws Exception {
        return this.userCfgService.selectUserCfgByUserId(this.getCurUserProp());
    }

    @RequestMapping(value = "/saveOrUpdateUserCfg.do")
    @ResponseBody
    public MessageResponse saveOrUpdateUserCfg(String jsons) throws Exception {
        List<UserCfg> list = JSON.parseArray(jsons, UserCfg.class);
        return this.userCfgService
                .saveOrUpdateUserCfg(list, this.getCurUserProp());
    }
}
