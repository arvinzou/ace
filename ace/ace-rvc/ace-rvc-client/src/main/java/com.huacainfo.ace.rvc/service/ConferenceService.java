package com.huacainfo.ace.rvc.service;

import com.huacainfo.ace.rvc.model.RvcConference;
import com.huacainfo.ace.rvc.model.RvcConferenceMembers;

import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/11/23.
 */
public interface ConferenceService {

    /**
     * 会议签到
     *
     * @param userId 用户ID
     * @param confId 会议ID
     * @return true/false
     */
    String signIn(String userId, String confId);

    /**
     * 创建会议
     *
     * @param userId     创建人用户ID -- 浪潮ID
     * @param conference 会议参数
     * @return RvcConference
     */
    RvcConference create(String userId, RvcConference conference);

    /**
     * 添加与会人员
     *
     * @param userId       操作人用户ID  -- 浪潮ID
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param members      与会人员ID数组 -- 浪潮ID
     * @return List<RvcConferenceMembers>
     */
    List<RvcConferenceMembers> addMembers(String userId, String conferenceId, String[] members);

    /**
     * 召开会议
     *
     * @param userId       操作人用户ID -- 浪潮
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return 处理结果
     */
    Map<String, Object> start(String userId, String conferenceId);

    /***
     * 加入会议
     *
     * @param userId 用户ID
     * @param conferenceId 会议ID -- -- rvc_conference.id
     * @return 处理结果
     */
    String join(String userId, String conferenceId);

    /***
     *  结束会议
     *
     * @param userId  用户ID
     * @param conferenceId 会议ID -- -- rvc_conference.id
     * @return 处理结果
     */
    String end(String userId, String conferenceId);
}
