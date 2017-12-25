package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.base.RvcBaseService;
import com.huacainfo.ace.rvc.dao.RvcBaseUserDao;
import com.huacainfo.ace.rvc.dao.RvcConferenceAddressMapper;
import com.huacainfo.ace.rvc.dao.RvcConferenceMapper;
import com.huacainfo.ace.rvc.dao.RvcConferenceMembersMapper;
import com.huacainfo.ace.rvc.kedapi.authorize.AuthorizeApi;
import com.huacainfo.ace.rvc.kedapi.common.constant.VideoConstant;
import com.huacainfo.ace.rvc.kedapi.control.ControlApi;
import com.huacainfo.ace.rvc.kedapi.control.model.RecordReq;
import com.huacainfo.ace.rvc.kedapi.control.model.conference.VideoConfResp;
import com.huacainfo.ace.rvc.kedapi.control.model.terminal.TerminalReq;
import com.huacainfo.ace.rvc.kedapi.control.model.terminal.TerminalResp;
import com.huacainfo.ace.rvc.kedapi.manage.ManageApi;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.Chairman;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.CreateRequest;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.InviteMember;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.Speaker;
import com.huacainfo.ace.rvc.kedapi.vrs.VRSApi;
import com.huacainfo.ace.rvc.kedapi.vrs.model.LiveRoomResp;
import com.huacainfo.ace.rvc.kedapi.vrs.model.LocalLoginResp;
import com.huacainfo.ace.rvc.model.RvcBaseUser;
import com.huacainfo.ace.rvc.model.RvcConference;
import com.huacainfo.ace.rvc.model.RvcConferenceAddress;
import com.huacainfo.ace.rvc.model.RvcConferenceMembers;
import com.huacainfo.ace.rvc.service.ConferenceService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import com.huacainfo.ace.rvc.vo.ConferenceDTO;
import com.huacainfo.ace.rvc.vo.ConferenceVO;
import com.huacainfo.ace.rvc.vo.JoinMember;
import com.huacainfo.ace.rvc.vo.SearchCondition;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/11/23.
 */
@Service("conferenceService")
public class ConferenceServiceImpl extends RvcBaseService implements ConferenceService {
    @Resource
    private RvcConferenceAddressMapper rvcConferenceAddressMapper;

    @Resource
    private RvcConferenceMembersMapper rvcConferenceMembersDao;

    @Autowired
    private RvcBaseUserDao rvcBaseUserDao;

    @Autowired
    private RvcConferenceMapper rvcConferenceDao;


    /**
     * 会议签到
     *
     * @param signInType 签到类型，'self' - 自我签到，'all' - 一键签到
     * @param userId     操作人用户id
     * @param confId     会议ID --rvc_conference.id
     * @param ids        一键签到ID，列表,id与id之前用','隔开，Demo：ID1,id2,id3
     * @return 处理结果
     */
    @Override
    public Map<String, Object> signIn(String signInType, String userId, String confId, String ids) {
        //自我签到
        if ("self".equals(signInType)) {

            RvcConferenceMembers members = rvcConferenceMembersDao.getByUserId(userId, confId);
            if (null == members) {
                return ResultUtil.fail(-1, "签到人员信息不存在");
            }
            members.setStatus("1");//签到
            members.setLastModifyDate(DateUtil.getNowDate());
            rvcConferenceMembersDao.updateByPrimaryKeySelective(members);

            return ResultUtil.success("签到成功");
        }//会议管理者一键签到
        else if ("all".equals(signInType)) {
            //此部分代码使用与部分签到
//            String[] idArray = ids.split(",");
//            RvcConferenceMembers members = null;// rvcConferenceMembersDao.getByUserId(userId, confId);
//            //一次性读取所有与会人员，全部签到
//            for (String id : idArray) {
//                members = rvcConferenceMembersDao.getByUserId(id, confId);
//                if (null != members) {
//                    members.setStatus("1");//签到
//                    members.setLastModifyDate(DateUtil.getNowDate());
//                    rvcConferenceMembersDao.updateByPrimaryKeySelective(members);
//                }
//            }

            Map<String, Object> params = new HashMap<>();
            params.put("conferenceId", confId);
            params.put("status", "1");
            params.put("lastModifyTime", DateUtil.getNowDate());
            int updateCount = rvcConferenceMembersDao.allSignIn(params);

            return ResultUtil.success("签到成功");
        } else {
            return ResultUtil.fail(-1, "未知签到类型");
        }
    }

    /**
     * 创建会议
     *
     * @param userId 创建人用户ID -- 浪潮ID
     * @param dto    会议参数
     * @return RvcConference
     */
    @Override
    public RvcConference create(String userId, ConferenceDTO dto) {
        RvcBaseUser user = rvcBaseUserDao.getByUserId(userId);
        if (null == user) {
            throw new CustomException("用户信息异常");
        }

        //默认创建人即为主持人
        RvcConference conference = new RvcConference();
        String conferenceId = GUIDUtil.getGUID();
        conference.setId(conferenceId);
        conference.setEmceeId(StringUtils.isEmpty(dto.getEmceeId()) ? userId : dto.getEmceeId());
        conference.setEmceeName(StringUtils.isEmpty(dto.getEmceeName()) ? user.getUserName() : dto.getEmceeName());
        conference.setBeginDate(StringUtils.isEmpty(dto.getBeginDate()) ?
                DateUtil.getNow() : dto.getBeginDate());
        conference.setCreateUserId(userId);
        conference.setCreateUserName(user.getUserName());
        conference.setCreateDate(DateUtil.getNowDate());
        conference.setLastModifyDate(DateUtil.getNowDate());
        conference.setStatus("0");
        //科达账户来源问题
        String kedaAccount = user.getKedaAccount();
        int kedaAccountType = user.getKedaAccountType();
        RvcConferenceAddress confAddress = null;
        if (StringUtils.isNotEmpty(dto.getAddressId())) {
            confAddress = rvcConferenceAddressMapper.selectByPrimaryKey(dto.getAddressId());
            if (null == confAddress) {
                throw new CustomException("所选会议地址信息异常");
            }
            kedaAccount = confAddress.getKedaAccount();
            kedaAccountType = confAddress.getKedaAccountType();
        }
        conference.setPoCode(null == confAddress ? user.getPoCode() : confAddress.getPoCode());
        conference.setPoName(null == confAddress ? user.getPoName() : confAddress.getPoName());
        conference.setAddressId(dto.getAddressId());
        conference.setAddressName(dto.getAddressName());
        conference.setKedaAccount(kedaAccount);
        confAddress.setKedaAccountType(kedaAccountType);
        rvcConferenceDao.insertSelective(conference);
        //加入参会人员
        createConferenceAddMember(user, conference, dto);

        return conference;
    }

    /**
     * 会议创建时，添加预设与会会员
     *
     * @param user       操作人员信息
     * @param conference 会议信息
     * @param dto        创会参数
     */
    private void createConferenceAddMember(RvcBaseUser user, RvcConference conference, ConferenceDTO dto) {
        //会议主场方
        RvcConferenceMembers conferenceMember = null;
        for (JoinMember item : dto.getInviteList()) {
            RvcBaseUser member = null;
            if (StringUtils.isNotEmpty(item.getUserId()))
                member = rvcBaseUserDao.selectByPrimaryKey(item.getUserId());

            conferenceMember = new RvcConferenceMembers();
            conferenceMember.setId(GUIDUtil.getGUID());
            conferenceMember.setConferenceId(conference.getId());
            conferenceMember.setUserId(item.getUserId());
            conferenceMember.setUserName(item.getUserName());
            conferenceMember.setKedaAccount(null == member ? "" : member.getKedaAccount());
            conferenceMember.setKedaAccountType(null == member ? null : member.getKedaAccountType());
            conferenceMember.setUserLevel(item.getLevel());//特邀嘉宾方/普通与会人员
            conferenceMember.setCreateUserId("system");
            conferenceMember.setCreateUserName("system");
            conferenceMember.setCreateDate(DateUtil.getNowDate());
            conferenceMember.setLastModifyDate(DateUtil.getNowDate());
            rvcConferenceMembersDao.insertSelective(conferenceMember);
        }
        //添加自己进入与会人员
        conferenceMember = new RvcConferenceMembers();
        conferenceMember.setId(GUIDUtil.getGUID());
        conferenceMember.setConferenceId(conference.getId());
        conferenceMember.setUserId(user.getUserId());
        conferenceMember.setUserName(user.getUserName());
        conferenceMember.setKedaAccount(user.getKedaAccount());
        conferenceMember.setKedaAccountType(user.getKedaAccountType());
        conferenceMember.setUserLevel("1");//特邀嘉宾方/普通与会人员
        conferenceMember.setCreateUserId("system");
        conferenceMember.setCreateUserName("system");
        conferenceMember.setCreateDate(DateUtil.getNowDate());
        conferenceMember.setLastModifyDate(DateUtil.getNowDate());
        rvcConferenceMembersDao.insertSelective(conferenceMember);

    }

    /**
     * 添加与会人员 --无接口操作
     *
     * @param userId     操作人用户ID  -- 浪潮ID
     * @param members    与会人员ID数组 -- 浪潮ID
     * @param conference 会议
     * @return List<RvcConferenceMembers>
     */
    private List<RvcConferenceMembers> addMembers(String userId, RvcConference conference, String[] members) {

        RvcBaseUser operateUser = rvcBaseUserDao.getByUserId(userId);
        if (null == operateUser) {
            throw new CustomException("操作用户信息异常");
        }
        List<RvcConferenceMembers> list = new ArrayList<>();
        RvcBaseUser member;
        RvcConferenceMembers confMember;
        for (String uid : members) {
            member = rvcBaseUserDao.getByUserId(uid);
            if (null == member) {
                throw new CustomException("与会人员用户信息异常[" + uid + "]");
            }
            //与会信息
            confMember = new RvcConferenceMembers();
            confMember.setId(GUIDUtil.getGUID());
            confMember.setConferenceId(conference.getId());
            confMember.setUserId(uid);
            confMember.setUserName(member.getUserName());
            confMember.setUserPosition(member.getPosition());
            confMember.setUserPhoneNumer(member.getPhoneNumer());
            confMember.setKedaAccount(member.getKedaAccount());
            confMember.setKedaAccountType(member.getKedaAccountType());
            confMember.setCreateUserId("system");
            confMember.setCreateUserName("system");
            confMember.setCreateDate(DateUtil.getNowDate());
            confMember.setLastModifyDate(DateUtil.getNowDate());
            confMember.setStatus("1");//普通参会人员
            rvcConferenceMembersDao.insertSelective(confMember);

            list.add(confMember);
        }

        return list;
    }

    /**
     * 添加与会人员 --无接口操作
     *
     * @param userId       操作人用户ID  -- 浪潮ID
     * @param members      与会人员ID数组 -- 浪潮ID
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return List<RvcConferenceMembers>
     */
    @Override
    public List<RvcConferenceMembers> addMembers(String userId, String conferenceId, String[] members) {
        RvcConference conference = rvcConferenceDao.selectByPrimaryKey(conferenceId);
        if (null == conference) {
            throw new CustomException("会议信息异常");
        }

        return addMembers(userId, conference, members);
    }

    /**
     * 召开会议
     *
     * @param userId       操作人用户ID -- 浪潮
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return 处理结果
     */
    @Override
    public Map<String, Object> start(String userId, String conferenceId) {
        //1.数据校验
        RvcBaseUser operateUser = rvcBaseUserDao.getByUserId(userId);
        if (null == operateUser) {
            throw new CustomException("操作用户信息异常");
        }
        RvcConference conference = rvcConferenceDao.selectByPrimaryKey(conferenceId);
        if (null == conference) {
            throw new CustomException("会议信息异常");
        }
        //非会议创建人，不允许当前操作
        if (!userId.equals(conference.getCreateUserId())) {
            return ResultUtil.fail(-1, "非会议创建人，不允许当前操作");
        }
        //会议已开始，直接返回播放地址
        if ("1".equals(conference.getStatus())) {
            return ResultUtil.success(conference.getLiveURL());
        }
        try {
            //可以放在调度器中去处理
            if (StringUtils.isEmpty(AuthorizeApi.ACCOUNT_TOKEN) || StringUtils.isEmpty(AuthorizeApi.SSO_COOKIE_KEY)) {
                AuthorizeApi.init();
            }
            //0.检测科达端是否存在同名会议，存在就shutDown掉，防止后面去直播地址有误
            checkConferenceStart(userId, conference);
            //2.调用接口开始会议，设置自己与会人员，发言人，主席
            String confId = apiCreate(operateUser, conference);//科达接口返回会议ID
            if ("fail".equals(confId)) {
                return ResultUtil.fail(-1, "接口创建会议失败");
            }
            //3.开启直播功能
            apiLive(confId, operateUser, conference);
            //4.调用录播接口，获取直播地址 --
            Thread.sleep(3000); //直播地址产生存在延迟（应答过程，分手动，自动；），停顿三秒后在获取
            String liveURL = getLiveAddress(conference);
            //5.更新会议状态
            conference.setStatus("1");
            conference.setLiveURL(liveURL);
            conference.setLastModifyUserId(operateUser.getUserId());
            conference.setLastModifyUserName(operateUser.getUserName());
            conference.setLastModifyDate(DateUtil.getNowDate());
            rvcConferenceDao.updateByPrimaryKeySelective(conference);

            return ResultUtil.success(liveURL);
        } catch (Exception e) {
            logger.error("start.error:{}", e);
            throw new CustomException("创建会议失败");
        }
    }

    /**
     * .检测科达端是否存在同名会议，存在就shutDown掉，防止后面去直播地址有误
     *
     * @param userId
     * @param conference
     */
    private void checkConferenceStart(String userId, RvcConference conference) {
        List<VideoConfResp> list = ControlApi.getList(0, 10,
                AuthorizeApi.ACCOUNT_TOKEN, AuthorizeApi.SSO_COOKIE_KEY);
        if (CollectionUtils.isNotEmpty(list)) {
            for (VideoConfResp item : list) {
                if (item.getName().equals(conference.getTitle())) {
                    ManageApi.delete(item.getConf_id(), AuthorizeApi.ACCOUNT_TOKEN, AuthorizeApi.SSO_COOKIE_KEY);
                }
            }
        }
    }

    /***
     *  指定默认发言人
     * @param confId 会议id -- 科达编号
     * @param operateUser 默认发言人会员信息
     * @param conference 会场信息
     */
    private void appointDefaultSpeaker(String confId, RvcBaseUser operateUser, RvcConference conference) {
        TerminalResp terminalResp = apiAddMember(confId, operateUser, conference);
        int mtId = terminalResp.getMts().get(0).getMt_id();
//        ControlApi.speaker(confId, mtId, AuthorizeApi.ACCOUNT_TOKEN, AuthorizeApi.SSO_COOKIE_KEY);

        RvcConferenceMembers members = rvcConferenceMembersDao.getByUserId(operateUser.getUserId(), confId);
        members.setMtId(mtId + "");
        members.setLastModifyDate(DateUtil.getNowDate());
        members.setLastModifyUserId(operateUser.getUserId());
        members.setLastModifyUserName(operateUser.getUserName());
        rvcConferenceMembersDao.updateByPrimaryKeySelective(members);
    }

    /***l
     * 加入会议
     * @param userId 用户ID
     * @param conferenceId 会议ID -- -- rvc_conference.id
     * @return 处理结果
     */
    @Override
    public String join(String userId, String conferenceId) {
        RvcBaseUser user = rvcBaseUserDao.getByUserId(userId);
        if (null == user) {
            throw new CustomException("用户资料异常");
        }
        RvcConference conference = rvcConferenceDao.selectByPrimaryKey(conferenceId);
        if (null == conference) {
            throw new CustomException("会议资料异常");
        }
        if ("1".equals(conference.getStatus()) || StringUtils.isEmpty(conference.getConfId())) {
            throw new CustomException("会议尚未开始");
        }
        //存在科达账户信息，邀请终端，参加会议
        if (!StringUtils.isEmpty(user.getKedaAccount())
                && StringUtils.isEmpty(user.getKedaAccountType() + "")) {
            TerminalResp resp = addMt(conference.getConfId(), user.getKedaAccount(), user.getKedaAccountType());
            int mtId = resp.getMts().get(0).getMt_id();
            //新加入成员新增信息，固有成员更新信息
            RvcConferenceMembers member = rvcConferenceMembersDao.getByUserId(userId, conferenceId);
            if (null == member) {
                member = new RvcConferenceMembers();
                member.setStatus("1");//签到状态
                member.setMtId(mtId + "");
                member.setId(GUIDUtil.getGUID());
                member.setConferenceId(conferenceId);
                member.setUserId(userId);
                member.setUserName(member.getUserName());
                member.setUserPosition(user.getPosition());
                member.setUserPhoneNumer(user.getPhoneNumer());
                member.setKedaAccount(member.getKedaAccount());
                member.setKedaAccountType(member.getKedaAccountType());
                member.setCreateUserId(userId);
                member.setCreateUserName(user.getUserName());
                member.setCreateDate(DateUtil.getNowDate());
                member.setLastModifyDate(DateUtil.getNowDate());
                rvcConferenceMembersDao.insertSelective(member);
            } else {
                member.setStatus("1");//签到状态
                member.setMtId(mtId + "");
                member.setLastModifyDate(DateUtil.getNowDate());
                member.setLastModifyUserId(user.getUserId());
                member.setLastModifyUserName(user.getUserName());
                rvcConferenceMembersDao.updateByPrimaryKeySelective(member);
            }
        }

        return conference.getLiveURL();
    }

    /***
     *  结束会议
     *
     * @param userId  用户ID
     * @param conferenceId 会议ID -- -- rvc_conference.id
     * @return 处理结果
     */
    @Override
    public String end(String userId, String conferenceId) {
        RvcBaseUser user = rvcBaseUserDao.getByUserId(userId);
        if (null == user) {
            throw new CustomException("用户资料异常");
        }
        RvcConference conference = rvcConferenceDao.selectByPrimaryKey(conferenceId);
        if (null == conference) {
            throw new CustomException("会议资料异常");
        }

        String isSuccess = ManageApi.delete(conference.getConfId(), AuthorizeApi.ACCOUNT_TOKEN, AuthorizeApi.SSO_COOKIE_KEY);
        if ("success".equals(isSuccess)) {

            conference.setStatus("2");
            conference.setLastModifyUserId(userId);
            conference.setLastModifyUserName(user.getUserName());
            conference.setLastModifyDate(DateUtil.getNowDate());

            rvcConferenceDao.updateByPrimaryKeySelective(conference);
        }

        return isSuccess;

    }

    /**
     * 获取会议列表
     *
     * @param userId 创建人用户ID -- 浪潮ID
     * @param status 会议状态，用','隔开
     *               0-未开始，1-进行中，2-已结束
     * @return RvcConference
     */
    @Override
    public List<RvcConference> getList(String userId, String status) {


        return rvcConferenceDao.getList(status.split(","));
    }

    /**
     * 获取视频会议地址
     *
     * @param userId 用户地址
     * @return List<RvcConferenceAddress>
     */
    @Override
    public List<RvcConferenceAddress> getAddressList(String userId) {
        return rvcConferenceAddressMapper.getAll();
    }

    /**
     * 搜索会议列表
     *
     * @param userId    当前用户ID
     * @param condition 查询条件
     * @return list
     */
    @Override
    public ConferenceVO search(String userId, SearchCondition condition) {
        int count;
        List<RvcConference> list;
        Map<String, Object> param = new HashMap<>();
        param.put("start", condition.getStart());
        param.put("limit", condition.getLimit());
        param.put("userId", userId);
        //搜索域
        String searchRegion = StringUtils.isEmpty(condition.getSearchRegion()) ?
                "related" : condition.getSearchRegion();
        //与我相关的
        if ("related".equals(searchRegion)) {
            param.put("region", "related");
        }//全部
        else if ("all".equals(searchRegion)) {
        }

        if (StringUtils.isNotEmpty(condition.getTitle())) {
            param.put("title", condition.getTitle());
            count = rvcConferenceDao.findCount(param);
        } else {
            count = rvcConferenceDao.findCount(param);
        }

        list = rvcConferenceDao.search(param);

        ConferenceVO vo = new ConferenceVO();
        vo.setTotal(count);
        vo.setRows(list);
        vo.setPageNo(condition.getStart());
        vo.setPageSize(null != list ? list.size() : 0);
        return vo;
    }

    /**
     * 获取与会人员列表
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID
     * @return list
     */
    @Override
    public Map<String, Object> getMemberList(String userId, String conferenceId) {

        List<RvcConferenceMembers> dataList = rvcConferenceMembersDao.getMemberList(conferenceId);

        return ResultUtil.success(dataList);
    }

    /**
     * 修改会议属性
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param dto          会议参数  @return list
     */
    @Override
    public Map<String, Object> modifyConference(String userId, String conferenceId, ConferenceDTO dto) {

        RvcBaseUser user = rvcBaseUserDao.getByUserId(userId);
        if (null == user) {
            ResultUtil.fail(-1, "用户信息异常");
        }
        RvcConference conference = rvcConferenceDao.selectByPrimaryKey(conferenceId);
        if (null == conference) {
            ResultUtil.fail(-1, "会议信息异常");
        }
        if (!"0".equals(conference.getStatus())) {
            ResultUtil.fail(-1, "会议已开始，信息不可修改");
        }
        if (!userId.equals(conference.getCreateUserId())) {
            ResultUtil.fail(-1, "当前操作人，非会议管理人员");
        }

        RvcConferenceAddress addressInfo = null;
        if (StringUtils.isNotEmpty(dto.getAddressId())) {
            addressInfo = rvcConferenceAddressMapper.selectByPrimaryKey(dto.getAddressId());
            if (null == addressInfo) {
                ResultUtil.fail(-1, "无效会议地址");
            }
        }
        conference.setTitle(dto.getTitle());
        conference.setSubtitle(dto.getSubtitle());
        conference.setEmceeId(dto.getEmceeId());
        conference.setEmceeName(dto.getEmceeName());
        conference.setAddressId(dto.getAddressId());
        conference.setAddressName(dto.getAddressName());
        conference.setPoCode(null == addressInfo ? conference.getPoCode() : addressInfo.getPoCode());
        conference.setPoName(null == addressInfo ? conference.getPoName() : addressInfo.getPoName());
        conference.setKedaAccount(null != addressInfo ? addressInfo.getKedaAccount() : "");
        conference.setKedaAccountType(null != addressInfo ? addressInfo.getKedaAccountType() : null);
        conference.setBeginDate(dto.getBeginDate());
        conference.setEndDate(dto.getEndDate());
        conference.setLastModifyUserId(userId);
        conference.setLastModifyUserName(user.getUserName());
        conference.setLastModifyDate(DateUtil.getNowDate());
        rvcConferenceDao.updateByPrimaryKeySelective(conference);

        //修改与会人员信息
        for (JoinMember item : dto.getInviteList()) {
            RvcBaseUser userMember = rvcBaseUserDao.getByUserId(item.getUserId());
            RvcConferenceMembers member = rvcConferenceMembersDao.getByUserId(item.getUserId(), conferenceId);
            if (null == member) {
                member = new RvcConferenceMembers();
                member.setId(GUIDUtil.getGUID());
                member.setConferenceId(conferenceId);
                member.setUserId(null == userMember ? item.getUserId() : userMember.getUserId());
                member.setUserName(null == userMember ? item.getUserName() : userMember.getUserName());
                member.setUserLevel(item.getLevel());
                member.setKedaAccount(null == userMember ? null : userMember.getKedaAccount());
                member.setKedaAccountType(null == userMember ? null : userMember.getKedaAccountType());
                member.setCreateDate(DateUtil.getNowDate());
                member.setCreateUserId("system");
                member.setCreateUserName("syetem");
                rvcConferenceMembersDao.insertSelective(member);
            } else {
                //添加还是移除
                if (item.isJoin()) {

                } else {
                    rvcConferenceMembersDao.deleteByPrimaryKey(member.getId());
                }
            }
        }

        return ResultUtil.success("修改成功");
    }

    /**
     * 删除会议
     *
     * @param userId       操作人用户id
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return result
     */
    @Override
    public Map<String, Object> deleteConference(String userId, String conferenceId) {

        RvcBaseUser user = rvcBaseUserDao.getByUserId(userId);
        if (null == user) {
            ResultUtil.fail(-1, "用户信息异常");
        }
        RvcConference conference = rvcConferenceDao.selectByPrimaryKey(conferenceId);
        if (null == conference) {
            ResultUtil.fail(-1, "会议信息异常");
        }
        if (!"0".equals(conference.getStatus())) {
            ResultUtil.fail(-1, "会议已开始，信息不可修改");
        }
        if (!userId.equals(conference.getCreateUserId())) {
            ResultUtil.fail(-1, "当前操作人，非会议管理人员");
        }

        conference.setStatus("3");//0-未开始，1-进行中，2-已结束, 3-已删除
        conference.setLastModifyUserId(userId);
        conference.setLastModifyUserName(user.getUserName());
        conference.setLastModifyDate(DateUtil.getNowDate());
        rvcConferenceDao.updateByPrimaryKeySelective(conference);

        return ResultUtil.success("删除成功");
    }


    /**
     * 获取直播地址
     *
     * @param conference 会议资料
     * @return 直播地址
     */
    private String getLiveAddress(RvcConference conference) {
        LocalLoginResp loginResp = VRSApi.localLogin(AuthorizeApi.ACCOUNT_TOKEN, AuthorizeApi.VRS_ACCOUNT, AuthorizeApi.VRS_PWD);
        String cookies = "SSO_COOKIE_KEY=" + loginResp.getToken();
        LiveRoomResp liveRoom = VRSApi.getLiveRoomList(AuthorizeApi.ACCOUNT_TOKEN, conference.getTitle(), cookies);

        return VRSApi.getLiveURL(liveRoom);
    }


    /**
     * 添加单个本级终端
     *
     * @param confId      科达会议id
     * @param operateUser 操作人信息
     * @param conference  会议资料
     * @return
     */
    private TerminalResp apiAddMember(String confId, RvcBaseUser operateUser, RvcConference conference) {
        return addMt(confId, operateUser.getKedaAccount(), operateUser.getKedaAccountType());
    }

    /**
     * 开启直播功能
     *
     * @param confId      科达会议ID
     * @param operateUser 操作人
     * @param conference  会议资料
     */
    private void apiLive(String confId, RvcBaseUser operateUser, RvcConference conference) {
        RecordReq req = new RecordReq(conference.getTitle(), VideoConstant.RecordMode.LIVE);
        ControlApi.record(confId, req, AuthorizeApi.ACCOUNT_TOKEN, AuthorizeApi.SSO_COOKIE_KEY);
    }

    /**
     * 调用接口开始会议
     *
     * @param operateUser 操作人信息
     * @param conference  预会议信息
     */
    private String apiCreate(RvcBaseUser operateUser, RvcConference conference) {
        //会议发言人，广播画面来源于此
        Speaker speaker = new Speaker(operateUser.getUserName(), conference.getKedaAccount(), conference.getKedaAccountType());
        //会议注意，管理员
        Chairman chairman = new Chairman(operateUser.getUserName(), conference.getKedaAccount(), conference.getKedaAccountType());
        //与会终端
        List<InviteMember> inviteMembers = new ArrayList<>();
        inviteMembers.add(new InviteMember(conference.getAddressName(), conference.getKedaAccount(), conference.getKedaAccountType()));

        CreateRequest request = new CreateRequest(conference.getTitle(), speaker, chairman, inviteMembers);
        String confId = ManageApi.create(AuthorizeApi.ACCOUNT_TOKEN, AuthorizeApi.SSO_COOKIE_KEY, request);
        conference.setConfId(confId);
        return confId;
    }


    /**
     * 接口添加单个本级终端
     *
     * @param confId      会议ID
     * @param account     账号
     * @param accountType 账号类型
     * @return 接口返回值
     */
    private TerminalResp addMt(String confId, String account, int accountType) {
        //添加本级终端
        List<TerminalReq> terminals = new ArrayList<>();
        TerminalReq t1 = new TerminalReq(account, accountType);
        terminals.add(t1);
        Map<String, List<TerminalReq>> params = new HashMap<>();
        params.put("mts", terminals);

        return ControlApi.addMts(confId, JsonUtil.toJson(params), AuthorizeApi.ACCOUNT_TOKEN, AuthorizeApi.SSO_COOKIE_KEY);
    }
}
