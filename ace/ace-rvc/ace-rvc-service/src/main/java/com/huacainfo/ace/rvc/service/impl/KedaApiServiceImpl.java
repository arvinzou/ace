package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.dao.RvcConferenceMapper;
import com.huacainfo.ace.rvc.model.RvcConference;
import com.huacainfo.ace.rvc.service.ConferenceService;
import com.huacainfo.ace.rvc.service.KedaApiService;
import com.huacainfo.ace.rvc.util.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Arvin on 2017/12/27.
 */
@Service("kedaApiServiceImpl")
public class KedaApiServiceImpl implements KedaApiService {

    @Resource
    private ConferenceService conferenceService;

    @Resource
    private RvcConferenceMapper rvcConferenceMapper;


    /**
     * 再次获取会议直播地址 -- 前提是会议已经开启过直播
     *
     * @param userId       当前操作人ID
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return 直播地址
     */
    @Override
    public Map<String, Object> getLiveURLAgain(String userId, String conferenceId) {
        RvcConference conference = rvcConferenceMapper.selectByPrimaryKey(conferenceId);
        if (null == conference) {
            return ResultUtil.fail(-1, "无效会议ID");
        }

        String liveURL = conferenceService.getLiveAddress(conference);
        if (StringUtils.isNotEmpty(liveURL)) {
            conference.setLiveURL(liveURL);
            conference.setLastModifyDate(DateUtil.getNowDate());
            conference.setLastModifyUserName("system");
            conference.setLastModifyUserId("system");
            rvcConferenceMapper.updateByPrimaryKeySelective(conference);

            return ResultUtil.success(liveURL);
        }

        return ResultUtil.fail(-1, "获取失败，请检查会议状态");
    }
}
