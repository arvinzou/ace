package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.rvc.dao.RvcConferenceChatLogMapper;
import com.huacainfo.ace.rvc.service.ChatLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Arvin on 2017/12/25.
 */
@Service("chatLogServiceImpl")
public class ChatLogServiceImpl implements ChatLogService {

    @Resource
    private RvcConferenceChatLogMapper rvcConferenceChatLogMapper;


}
