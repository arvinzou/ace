package com.huacainfo.ace.rvc.service;

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
}
