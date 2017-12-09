package com.huacainfo.ace.rvc.web.controller;

import com.huacainfo.ace.rvc.model.RvcConference;
import com.huacainfo.ace.rvc.model.RvcConferenceMembers;
import com.huacainfo.ace.rvc.service.ConferenceService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import com.huacainfo.ace.rvc.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/12/7.
 */
@Controller
@RequestMapping("/conference")
public class ConferenceController extends BaseController {


    @Autowired
    private ConferenceService conferenceService;

    /**
     * 创建会议
     *
     * @param userId     创建人用户ID -- 浪潮ID
     * @param conference 会议参数
     * @return RvcConference
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map<String, Object> create(String userId, RvcConference conference) throws Exception {
        RvcConference rvcConference = conferenceService.create(userId, conference);

        return ResultUtil.success(rvcConference);
    }

    /**
     * 添加与会人员
     *
     * @param userId       操作人用户ID  -- 浪潮ID
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param members      与会人员ID数组 -- 浪潮ID  Demo:id1,id2,id3
     * @return List<RvcConferenceMembers>
     */
    @ResponseBody
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public Map<String, Object> create(String userId, String conferenceId, String members) throws Exception {
        List<RvcConferenceMembers> list = conferenceService.addMembers(userId, conferenceId, members.split(","));

        return ResultUtil.success(list);
    }


    /**
     * 召开会议
     *
     * @param userId       操作人用户ID -- 浪潮
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public Map<String, Object> start(String userId, String conferenceId) throws Exception {

        return conferenceService.start(userId, conferenceId);
    }

    /***
     * 加入会议
     *
     * @param userId 用户ID
     * @param conferenceId 会议ID -- -- rvc_conference.id
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Map<String, Object> join(String userId, String conferenceId) throws Exception {

        return ResultUtil.success(conferenceService.join(userId, conferenceId));
    }

    /***
     *  结束会议
     *
     * @param userId  用户ID
     * @param conferenceId 会议ID -- -- rvc_conference.id
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "/end", method = RequestMethod.POST)
    public Map<String, Object> end(String userId, String conferenceId) throws Exception {

        return ResultUtil.success(conferenceService.end(userId, conferenceId));
    }
}
