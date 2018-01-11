package com.huacainfo.ace.rvc.service;

import com.huacainfo.ace.rvc.model.RvcConference;
import com.huacainfo.ace.rvc.model.RvcConferenceAddress;
import com.huacainfo.ace.rvc.model.RvcConferenceMembers;
import com.huacainfo.ace.rvc.vo.ConferenceDTO;
import com.huacainfo.ace.rvc.vo.ConferenceVO;
import com.huacainfo.ace.rvc.vo.SearchCondition;

import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/11/23.
 */
public interface ConferenceService {

    /**
     * 会议签到
     *
     * @param signInType 签到类型，'self' - 自我签到，'all' - 一键签到
     * @param userId     操作人用户id
     * @param confId     会议ID
     * @param ids        一键签到ID，列表,id与id之前用','隔开，Demo：ID1,id2,id3
     * @return 处理结果
     */
    Map<String, Object> signIn(String signInType, String userId, String confId, String ids);

    /**
     * 创建会议
     *
     * @param userId     创建人用户ID -- 浪潮ID
     * @param conference 会议参数
     * @return RvcConference
     */
    RvcConference create(String userId, ConferenceDTO conference);

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

    /**
     * 获取会议列表
     *
     * @param userId 创建人用户ID -- 浪潮ID
     * @param status 会议状态，用','隔开
     *               0-未开始，1-进行中，2-已结束
     * @return RvcConference
     */
    List<RvcConference> getList(String userId, String status);

    /**
     * 获取视频会议地址
     *
     * @param userId 用户地址
     * @return List<RvcConferenceAddress>
     */
    List<RvcConferenceAddress> getAddressList(String userId);

    /**
     * 获取与会人员列表
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID
     * @return list
     */
    Map<String, Object> getMemberList(String userId, String conferenceId);

    /**
     * 修改会议属性
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param dto          会议参数  @return list
     */
    Map<String, Object> modifyConference(String userId, String conferenceId, ConferenceDTO dto);

    /**
     * 删除会议
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return result
     */
    Map<String, Object> deleteConference(String userId, String conferenceId);

    /**
     * 搜索会议列表
     *
     * @param userId    当前用户ID
     * @param condition 查询条件
     * @return list
     */
    ConferenceVO search(String userId, SearchCondition condition);

    /**
     * 获取单个会议信息
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return result
     */
    Map<String,Object> get(String userId, String conferenceId);

    /**
     * 获取直播地址
     *
     * @param conference 会议资料
     * @return 直播地址
     */
    String getLiveAddress(RvcConference conference);

    /**
     * 会议录制
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param action 动作代码：start - 开启录制,stop - 关闭录制
     * @return result
     */
    Map<String, Object> record(String userId, String conferenceId, String action);
}
