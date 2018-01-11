package com.huacainfo.ace.rvc.service;

import com.huacainfo.ace.rvc.vo.ChatDTO;

/**
 * Created by Arvin on 2017/12/25.
 */
public interface ChatLogService {

    /**
     * 转换用户消息
     *
     * @param chatDTO rid      房间ID -- 会议ID - rvc_conference.id
     *                uid      用户ID -- rvc_base_user.userId
     * @return 返回给客户端内容文本
     */
    ChatDTO parseMessage(ChatDTO chatDTO);

    /**
     * 获取欢迎语句
     *
     * @param rid  房间ID -- 会议ID - rvc_conference.id
     * @param uid 用户ID -- rvc_base_user.userId
     * @return String
     */
    ChatDTO getWelcomeStatement(String rid, String uid);
}
