package com.huacainfo.ace.rvc.service;

import java.util.Map;

/**
 * Created by Arvin on 2017/12/27.
 */
public interface KedaApiService {

    /**
     * 再次获取会议直播地址 -- 前提是会议已经开启过直播
     *
     * @param userId       当前操作人ID
     * @param conferenceId 会议ID -- rvc_conference.id
     * @return 直播地址
     */
    Map<String, Object> getLiveURLAgain(String userId, String conferenceId);
}
