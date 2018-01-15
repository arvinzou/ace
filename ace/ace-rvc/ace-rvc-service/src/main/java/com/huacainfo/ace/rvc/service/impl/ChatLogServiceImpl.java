package com.huacainfo.ace.rvc.service.impl;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.rvc.dao.RvcConferenceChatLogMapper;
import com.huacainfo.ace.rvc.model.RvcBaseUser;
import com.huacainfo.ace.rvc.model.RvcConferenceChatLog;
import com.huacainfo.ace.rvc.service.ChatLogService;
import com.huacainfo.ace.rvc.service.FileService;
import com.huacainfo.ace.rvc.service.UserService;
import com.huacainfo.ace.rvc.vo.ChatDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Arvin on 2017/12/25.
 */
@Service("chatLogServiceImpl")
public class ChatLogServiceImpl implements ChatLogService {
    @Resource
    private FileService fileService;

    @Resource
    private RvcConferenceChatLogMapper rvcConferenceChatLogMapper;

    @Resource
    private UserService userService;


    /**
     * 转换用户消息
     *
     * @param chatDTO rid      房间ID -- 会议ID - rvc_conference.id
     *                uid      用户ID -- rvc_base_user.userId
     * @return 返回给客户端内容文本
     */
    @Override
    public ChatDTO parseMessage(ChatDTO chatDTO) {
        //return to client
        ChatDTO rtn;
        //用户信息
        RvcBaseUser user = userService.getByUserIdWithRedis(chatDTO.getUid());
        if (null == user) {
            rtn = new ChatDTO(chatDTO.getRid(), chatDTO.getUid(),
                    ChatDTO.ACTION_ERROR, "用户信息不存在", "");
            return rtn;
        }
        //是否是图片，需做文件上传，获取URL地址
//        boolean isFile = ChatDTO.ACTION_FILE.equals(chatDTO.getAction()) ||
//                ChatDTO.ACTION_IMAGE.equals(chatDTO.getAction());
//        String fileURL = uploadFile(isFile, chatDTO.getContent(), chatDTO.getSuffix());
//        if (isFile) {
//            chatDTO.setContent(fileURL);
//        }
        //存储聊天记录
        insertLog(user, chatDTO);
        //转换文本
        String replyTxt = chatDTO.getContent();//isFile ? fileURL : chatDTO.getContent();
//        parseImage(user, fileURL) :parseText(user, chatDTO.getContent());

        //return to client
        rtn = new ChatDTO(chatDTO.getRid(), chatDTO.getUid(),
                chatDTO.getAction(), replyTxt, user.getPortrait());
        rtn.setSpeakerName(user.getUserName());
        return rtn;
    }

    /**
     * 获取欢迎语句
     *
     * @param rid 房间ID -- 会议ID - rvc_conference.id
     * @param uid 用户ID -- rvc_base_user.userId
     * @return JJ
     */
    @Override
    public ChatDTO getWelcomeStatement(String rid, String uid) {
        RvcBaseUser user = userService.getByUserIdWithRedis(uid);
        String nickName = "游客";
        if (null != user) {
            nickName = user.getUserName();
        }

        String welcome = nickName + "已经入聊天室，大家欢迎！";
        ChatDTO cm = new ChatDTO(rid, uid, ChatDTO.ACTION_TEXT, welcome,
                null == user ? "" : user.getPortrait());
        return cm;
    }

    /**
     * 文件上传
     *
     * @param isImage  是否是图片
     * @param userText 用户键入文本
     * @param suffix
     * @return 文件URL地址
     */
    private String uploadFile(boolean isImage, String userText, String suffix) {
        if (isImage) {
//            String base64Str = userText;

            String url = fileService.upload(userText, GUIDUtil.getGUID(), suffix,
                    "", "");//TODO 文件上传获取图片地址

            return url;
        }

        return "";
    }

    /**
     * 插入聊天日志
     *
     * @param user        用户信息
     * @param chatMessage 用户键入信息
     */
    private void insertLog(RvcBaseUser user, ChatDTO chatMessage) {
        RvcConferenceChatLog log = new RvcConferenceChatLog();
        log.setId(GUIDUtil.getGUID());
        log.setRoomId(chatMessage.getRid());
        log.setUserId(user.getUserId());
        log.setUserName(user.getUserName());
        log.setContent(chatMessage.getContent());
        log.setCreateDate(DateUtil.getNowDate());
        log.setCreateUserId("system");
        log.setCreateUserName("system");
        rvcConferenceChatLogMapper.insertSelective(log);
    }


//    /**
//     * 转义图片文本
//     *
//     * @param imageURL 图片Base64 字符串
//     * @param user     发送人用户信息
//     * @return replyText
//     */
//    private String parseImage(RvcBaseUser user, String imageURL) {
//
//        String imageHtml = "<img src='" + imageURL + "'>";
//
//        return parseText(user, imageHtml);
//    }
//
//    /**
//     * 转义文本内容
//     *
//     * @param user 发送人用户信息
//     * @param text 发送文本内容
//     * @return replyText
//     */
//    private String parseText(RvcBaseUser user, String text) {
//
//        text = user.getUserName() + ":" + text;
//
//        return text;
//    }
}
