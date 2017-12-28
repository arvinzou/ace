package com.huacainfo.ace.rvc.web.controller;

import com.huacainfo.ace.rvc.service.KedaApiService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import com.huacainfo.ace.rvc.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Arvin on 2017/12/27.
 */
@Controller("kedaApiController")
@RequestMapping("/keda")
public class KedaApiController extends BaseController {
    @Resource
    private KedaApiService kedaApiService;


    /**
     * 再次获取会议直播地址 -- 前提是会议已经开启过直播
     *
     * @param userId       当前操作人ID
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return 直播地址
     */
    @RequestMapping("/getLiveURL")
    public Map<String, Object> getLiveURLAgain(String userId, String conferenceId) {

        try {
            return kedaApiService.getLiveURLAgain(userId, conferenceId);
        } catch (Exception e) {
            logger.error("KedaApiController.getLiveURLAgain.error:{}", e);
            return ResultUtil.fail(-1, "系统错误");
        }
    }
}
