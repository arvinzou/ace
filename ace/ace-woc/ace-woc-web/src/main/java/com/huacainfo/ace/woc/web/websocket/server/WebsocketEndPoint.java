package com.huacainfo.ace.woc.web.websocket.server;

import com.huacainfo.ace.woc.common.utils.SessionUtils;
import com.huacainfo.ace.woc.vo.SocketDTO;
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

// rid 和 uid 是我的业务标识参数, /websocket 是连接的路径，可以自行定义

@ServerEndpoint(value = "/www/websocket/{rid}/{uid}")
public class WebsocketEndPoint {


    //日志处理
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


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

        //
        String content = "[" + uid + "],接入成功";
        SocketDTO welcome = new SocketDTO(rid, uid, SocketDTO.ACTION_TEXT, content, "");

        //群发
        broadcast(rid, uid, welcome);
    }


    /**
     * 收到客户端消息时触发
     *
     * @param rid     房间ID
     * @param uid     个人ID
     * @param message 请求消息体
     */
    @OnMessage
    public void onMessage(@PathParam("rid") String rid, @PathParam("uid") String uid, String message) {

        String content = "rid:" + rid + "uid:" + uid + ",Got you message:" + message;
        logger.info(content);


        SocketDTO reply = new SocketDTO(rid, uid, SocketDTO.ACTION_TEXT, content, "");
        sendMessage(rid, uid, reply.toString());
    }


    /**
     * 异常时触发
     *
     * @param rid     房间ID
     * @param uid     个人ID
     * @param session session会话
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
     * @param rid     房间ID
     * @param uid     个人ID
     * @param session session会话
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
        }
    }


    /**
     * 广播消息
     *
     * @param rid   发送房间号
     * @param uid   发送人表示
     * @param reply 回复对象
     */
    private void broadcast(String rid, String uid, SocketDTO reply) {
//        String key;
//        Session client;
        String[] keyArray;
        for (Map.Entry<String, Session> entry : SessionUtils.clients.entrySet()) {
            keyArray = entry.getKey().split("_");
            if (uid.equals(keyArray[1])) {
                reply.setSelf(true);
                sendMessage(keyArray[0], keyArray[1], reply.toString());
            } else {
                reply.setSelf(false);
                sendMessage(keyArray[0], keyArray[1], reply.toString());
            }
        }
    }

}
