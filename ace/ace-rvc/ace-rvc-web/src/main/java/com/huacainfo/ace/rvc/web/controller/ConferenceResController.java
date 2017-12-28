package com.huacainfo.ace.rvc.web.controller;

import com.huacainfo.ace.rvc.service.ConferenceResService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import com.huacainfo.ace.rvc.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Arvin on 2017/12/22.
 */
@Controller
@RequestMapping("/conference/res")
public class ConferenceResController extends BaseController {

    @Resource
    private ConferenceResService conferenceResService;

    /**
     * 获取会议资源
     *
     * @param resType      资源类型
     *                     1-文本内容，2-图片，3-文件，4-视频，5-会议纪要,可多选，多状态间","隔开
     * @param userId       操作人用户id
     * @param conferenceId 会议ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Map<String, Object> get(String resType, String userId, String conferenceId) {

        try {
            return conferenceResService.get(resType, userId, conferenceId);
        } catch (Exception e) {
            logger.error("ConferenceResController.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
    }

}
