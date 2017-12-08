package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.rvc.kedapi.control.ControlApi;
import com.huacainfo.ace.rvc.kedapi.control.model.TerminalReq;
import com.huacainfo.ace.rvc.service.ConferenceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/11/23.
 */
@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService {


    /**
     * 会议签到
     *
     * @param userId 用户ID
     * @param confId 会议ID
     * @return true/false
     */
    @Override
    public String signIn(String userId, String confId) {

        return null;
    }


    private void addMts(String confId, String account, int accountType, String token, String cookies) {
        //添加本级终端
        List<TerminalReq> terminals = new ArrayList<>();
        TerminalReq t1 = new TerminalReq();
        t1.setAccount(account);
        t1.setAccount_type(accountType);//IP地址
        t1.setBitrate(2048);
        t1.setProtocol(0);
        t1.setForced_call(0);
        terminals.add(t1);

        Map<String, List<TerminalReq>> params = new HashMap<>();
        params.put("mts", terminals);
        ControlApi.addMts("7360036", JsonUtil.toJson(params), token, cookies);
    }
}
