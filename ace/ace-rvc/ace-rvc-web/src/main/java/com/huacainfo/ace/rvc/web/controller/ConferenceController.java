package com.huacainfo.ace.rvc.web.controller;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.model.RvcConference;
import com.huacainfo.ace.rvc.model.RvcConferenceAddress;
import com.huacainfo.ace.rvc.model.RvcConferenceMembers;
import com.huacainfo.ace.rvc.service.ConferenceService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import com.huacainfo.ace.rvc.vo.ConferenceDTO;
import com.huacainfo.ace.rvc.vo.ConferenceVO;
import com.huacainfo.ace.rvc.vo.SearchCondition;
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
     * 会议录制
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param action       动作代码：start - 开启录制,stop - 关闭录制
     * @return result
     */
    @ResponseBody
    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public Map<String, Object> record(String userId, String conferenceId, String action) {

        try {
            return ResultUtil.success(conferenceService.record(userId, conferenceId, action));
        } catch (Exception e) {
            logger.error("ConferenceController.record.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
    }


    /**
     * 获取单个会议信息
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return result
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Map<String, Object> get(String userId, String conferenceId) {

        try {
            return conferenceService.get(userId, conferenceId);
        } catch (Exception e) {
            logger.error("ConferenceController.get.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }

    }


    /**
     * 删除会议
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return result
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, Object> deleteConference(String userId, String conferenceId) {

        try {
            return conferenceService.deleteConference(userId, conferenceId);
        } catch (Exception e) {
            logger.error("ConferenceController.deleteConference.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
    }

    /**
     * 修改会议属性
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param dto          会议参数
     * @return list
     */
    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public Map<String, Object> modifyConference(String userId, String conferenceId, String dto) {

        try {
            return conferenceService.modifyConference(userId, conferenceId,
                    JsonUtil.toObject(dto, ConferenceDTO.class));
        } catch (Exception e) {
            logger.error("ConferenceController.modifyConference.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }

    }

    /**
     * 获取与会人员列表
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID
     * @return list
     */
    @ResponseBody
    @RequestMapping(value = "/getMemberList", method = RequestMethod.POST)
    public Map<String, Object> getMemberList(String userId, String conferenceId) {


        try {
            return conferenceService.getMemberList(userId, conferenceId);
        } catch (Exception e) {
            logger.error("ConferenceController.getMemberList.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
    }


    /**
     * 会议签到
     *
     * @param signInType   签到类型，'self' - 自我签到，'all' - 一键签到
     * @param userId       操作人用户id
     * @param conferenceId 会议ID --rvc_conference.id
     * @param ids          一键签到ID，列表,id与id之前用','隔开，Demo：ID1,id2,id3
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public Map<String, Object> signIn(String signInType, String userId, String conferenceId, String ids) {

        try {
            return conferenceService.signIn(signInType, userId, conferenceId, ids);
        } catch (Exception e) {
            logger.error("ConferenceController.signIn.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }

    }


    /**
     * 搜索会议列表
     *
     * @param userId    当前用户ID
     * @param condition 查询条件
     * @return list
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Map<String, Object> search(String userId, String condition) {


        try {

            ConferenceVO data = conferenceService.search(userId, JsonUtil.toObject(condition, SearchCondition.class));

            return ResultUtil.success(data);
        } catch (Exception e) {
            logger.error("ConferenceController.search.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
    }

    /**
     * 获取会议地址
     *
     * @param userId 用户ID
     * @return list
     */
    @ResponseBody
    @RequestMapping(value = "/getAddrList", method = RequestMethod.GET)
    public Map<String, Object> getAddressList(String userId) {

        try {

            List<RvcConferenceAddress> list = conferenceService.getAddressList(userId);

            return ResultUtil.success(list);
        } catch (Exception e) {
            logger.error("ConferenceController.getAddressList.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }

    }

    /**
     * 获取会议列表
     *
     * @param userId 创建人用户ID -- 浪潮ID
     * @param status 状态值：0-待召开，1-正在召开，2-已结束
     * @return RvcConference
     */
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Map<String, Object> getList(String userId, String status) throws Exception {
        if (StringUtils.isEmpty(status)) {
            return ResultUtil.fail(-1, "params error");
        }

        try {

            List<RvcConference> rvcConference = conferenceService.getList(userId, status);

            return ResultUtil.success(rvcConference);
        } catch (Exception e) {
            logger.error("ConferenceController.getList.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }

    }

    /**
     * 创建会议
     *
     * @param userId     创建人用户ID -- 浪潮ID
     * @param conference 会议参数   Demo:
     *                   {"address":"湖南省常德市武陵区","addressId":"AE8E8E8EC8C8","beginDate":"2017-12-20 17:19:01","endDate":"2017-12-20 17:19:01","title":"接口创建测试会议"}
     * @return RvcConference
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map<String, Object> create(String userId, String conference) throws Exception {


        try {

            RvcConference rvcConference = conferenceService.create(userId, JsonUtil.toObject(conference, ConferenceDTO.class));

            return ResultUtil.success(rvcConference);
        } catch (Exception e) {
            logger.error("ConferenceController.create.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
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
    public Map<String, Object> addMember(String userId, String conferenceId, String members) throws Exception {


        try {

            List<RvcConferenceMembers> list = conferenceService.addMembers(userId, conferenceId, members.split(","));

            return ResultUtil.success(list);
        } catch (Exception e) {
            logger.error("ConferenceController.addMember.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
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

        try {

            return conferenceService.start(userId, conferenceId);
        } catch (Exception e) {
            logger.error("ConferenceController.start.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }
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

        try {

            return ResultUtil.success(conferenceService.join(userId, conferenceId));
        } catch (Exception e) {
            logger.error("ConferenceController.join.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }

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

        try {

            return ResultUtil.success(conferenceService.end(userId, conferenceId));
        } catch (Exception e) {
            logger.error("ConferenceController.end.error:{}", e);

            return ResultUtil.fail(-1, "系统错误");
        }

    }
}
