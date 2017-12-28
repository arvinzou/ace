package com.huacainfo.ace.rvc.web.websocket.server;

import com.huacainfo.ace.rvc.web.common.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;

/**
 * 功能说明：websocket处理类, 使用J2EE7的标准
 * <p>
 * 切忌直接在该连接处理类中加入业务处理代码
 * <p>
 */

// rid 和 uid 是我的业务标识参数, /live/websocket 是连接的路径，可以自行定义

@ServerEndpoint("/live/websocket/{rid}/{uid}")
public class WebsocketEndPoint {

    //日志处理
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int TEXT = 1;
    private final int IMAGE = 2;
    private final int FILE = 3;

    /**
     * 打开连接时触发
     *
     * @param rid     房间号
     * @param uid     用户唯一识别ID
     * @param session 用户session
     */
    @OnOpen
    public void onOpen(@PathParam("rid") String rid, @PathParam("uid") String uid, Session session) {
        logger.info("Websocket Start Connecting:" + SessionUtils.getKey(rid, uid));
        //放入群发列表
        SessionUtils.put(rid, uid, session);

        String notice = uid + "已进入聊天室，大家欢迎!~";
        //
        broadcast(notice, this.TEXT);
    }


    /**
     * 收到客户端消息时触发
     *
     * @param rid
     * @param uid
     * @param message
     * @return
     */
    @OnMessage
    public void onMessage(@PathParam("rid") String rid, @PathParam("uid") String uid, String message) {
        logger.info("uid:" + uid + ",Got you message:" + message);
        if (message.startsWith("image:")) {
            String imageURI = "http://zx.huacainfo.com/group1/M00/00/11/i-AA41ow1qeAF4JuAACEiGSVNx8324.jpg?filename=%E5%9F%B9%E8%AE%AD%E6%95%99%E8%82%B21.jpg";
            //发送图片
            broadcast(imageURI, this.IMAGE);
        } else {
            broadcast(uid + " say:" + message, this.TEXT);
        }
    }


    /**
     * 异常时触发
     *
     * @param rid
     * @param uid
     * @param session
     */
    @OnError
    public void onError(@PathParam("rid") String rid, @PathParam("uid") String uid,
                        Throwable throwable, Session session) {

        logger.info("Websocket Connection Exception:" + SessionUtils.getKey(rid, uid));
        logger.info(throwable.getMessage(), throwable);

        SessionUtils.remove(rid, uid);
    }


    /**
     * 关闭连接时触发
     *
     * @param rid
     * @param uid
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("rid") String rid, @PathParam("uid") String uid, Session session) {
        logger.info("Websocket Close Connection:" + SessionUtils.getKey(rid, uid));
        SessionUtils.remove(rid, uid);
    }


    /**
     * 将数据传回客户端
     * <p>
     * 异步的方式
     *
     * @param rid
     * @param uid
     * @param message
     */
    private void sendMessage(String rid, String uid, String message) {
        if (SessionUtils.hasConnection(rid, uid)) {
            //异步推送
            SessionUtils.get(rid, uid).getAsyncRemote().sendText(message);
            //同步推送
//            SessionUtils.get(rid,uid).getBasicRemote().sendText(message);
        } else {
            logger.info(SessionUtils.getKey(rid, uid) + "Connection does not exist");
//            throw new NullPointerException(SessionUtils.getKey(rid, uid) + "Connection does not exist");
        }
    }

    /**
     * 发送图片
     *
     * @param imageUri 图片URL地址
     */
    private void sendImage(String rid, String uid, String imageUri) {

        String imageContent = "<img src='" + imageUri + "'>";
        sendMessage(rid, uid, imageContent);
    }


    /**
     * 广播消息
     *
     * @param message 消息主体
     * @param type    消息类型
     */
    private void broadcast(String message, int type) {
//        String key;
//        Session client;
        String[] keyArray;
        for (Map.Entry<String, Session> entry : SessionUtils.clients.entrySet()) {
            keyArray = entry.getKey().split("_");
            switch (type) {
                case TEXT://文本发送
                    sendMessage(keyArray[0], keyArray[1], message);
                    break;
                case IMAGE://图片发送
                    sendImage(keyArray[0], keyArray[1], message);
                    break;
                default:
                    break;
            }

        }
    }
}
